package br.com.catalogo.produtos.util;

import java.text.Normalizer;

public final class TextNormalizer {

    private TextNormalizer() {}

    /**
    * Normaliza um texto, removendo acentos e convertendo para letras minúsculas.
    * Por exemplo, "Água" se torna "agua".
    * @param text O texto a ser normalizado.
    * @return O texto normalizado ou null se o texto de entrada for null.
    */
    public static String normalizeText(String text) {
        if (text == null) return null;
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "")
                .toLowerCase();
    }

}
