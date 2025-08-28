package br.com.catalogo.produtos.core.usecase;

import br.com.catalogo.produtos.adapter.in.request.ProductInsertRequest;
import br.com.catalogo.produtos.adapter.in.request.ProductRequest;
import br.com.catalogo.produtos.response.ProductResponse;
import br.com.catalogo.produtos.adapter.out.repository.entity.Product;
import br.com.catalogo.produtos.exception.ConflictException;
import br.com.catalogo.produtos.exception.NotFoundException;
import br.com.catalogo.produtos.filter.ProductFilter;
import br.com.catalogo.produtos.mapper.ProductMapper;
import br.com.catalogo.produtos.adapter.out.repository.ProductRepository;
import br.com.catalogo.produtos.spec.ProductSpec;
import br.com.catalogo.produtos.util.TextNormalizer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductUseCase {

    private final ProductMapper mapper;
    private final ProductRepository repository;
    private final CategoryUseCase categoryUseCase;

    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(ProductFilter filter, Pageable pageable) {
        return this.repository.findAll(
                ProductSpec.withFilter(filter),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return this.mapper.toDTO(
                this.repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Produto com ID '%s' não encontrado!".formatted(id)
                        )));
    }

    @Transactional(readOnly = true)
    public Product findEntityById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Produto com ID '%s' não encontrado!".formatted(id)
                ));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findByName(String name, Pageable pageable) {
        return this.repository.findByNameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findByCategoryId(Long id, Pageable pageable) {
        return this.repository.findByCategory_Id(
                id,
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findByCategoryName(String name, Pageable pageable) {
        return this.repository.findByCategory_NameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional
    public ProductResponse create(ProductInsertRequest dto) {
        this.nameExists(dto.getName());
        Product product = this.mapper.toEntity(dto);
        product.setCategory(this.categoryUseCase.findEntityById(dto.getCategoryId()));
        return this.mapper.toDTO(this.repository.save(product));
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest dto) {
        Product product = this.findEntityById(id);

        this.nameExists(product.getName(), dto.getName());

        this.mapper.updateFromDto(dto, product);

        if (dto.getCategoryId() != null) {
            product.setCategory(this.categoryUseCase.findEntityById(dto.getCategoryId()));
        }

        return this.mapper.toDTO(this.repository.save(product));
    }

    @Transactional
    public ProductResponse delete(Long id) {
        ProductResponse dto = this.findById(id);
        this.repository.deleteById(id);
        return dto;
    }

    public byte[] generateBarcodeByEAN(String ean) {
        try {
            ean = this.validateEan(ean);
            BitMatrix matrix = new MultiFormatWriter().encode(ean, BarcodeFormat.EAN_13, 300, 150);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o código de barras. " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public byte[] generateBarcodeByProductId(Long id) {
        return this.generateBarcodeByEAN(this.findById(id).getEan());
    }

    private String validateEan(String ean) {
        if (!ean.matches("\\d{13}")) {
            throw new IllegalArgumentException("EAN deve ter exatamente 13 dígitos numéricos.");
        }

        String eanWithoutDigit = ean.substring(0, 12);

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(ean.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int digit = (10 - (sum % 10)) % 10;

        String correctEan = eanWithoutDigit + digit;

        if (!(correctEan).equals(ean)) {
            log.warn("EAN com digito inválido.\nEAN ..........: %s\nEAN Correto ..: %s".formatted(
                    ean,
                    correctEan
            ));
        }
        return correctEan;
    }

    private void nameExists(String name) {
        if (this.repository.existsByNameIgnoreCase(name)) {
            throw new ConflictException("Produto '%s' já está cadastrado!".formatted(name));
        }
    }

    private void nameExists(String originalName, String newName) {
        if (originalName.equals(newName)) return;
        if (this.repository.existsByNameIgnoreCase(newName)) {
            throw new ConflictException("Produto '%s' já está cadastrado!".formatted(newName));
        }
    }

}
