package br.com.prothera.desafio.formatacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Formatador {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String PADRAO_NUMERICO = "#,##0.00";
    private static final char SEPARADOR_MILHAR = '.';
    private static final char SEPARADOR_DECIMAL = ',';

    private Formatador() {
    }

    public static String data(LocalDate data) {
        return FORMATO_DATA.format(Objects.requireNonNull(data, "A data é obrigatória"));
    }

    public static String numero(BigDecimal valor) {
        Objects.requireNonNull(valor, "O valor é obrigatório");
        return criarFormatoNumerico().format(valor);
    }

    private static DecimalFormat criarFormatoNumerico() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator(SEPARADOR_MILHAR);
        simbolos.setDecimalSeparator(SEPARADOR_DECIMAL);
        DecimalFormat formato = new DecimalFormat(PADRAO_NUMERICO, simbolos);
        formato.setRoundingMode(RoundingMode.HALF_UP);
        return formato;
    }
}
