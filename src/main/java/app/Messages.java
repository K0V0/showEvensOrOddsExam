package app;

public class Messages {
    public static final String ERROR_NO_ARGUMENTS = Utils.composeMultilineString(
            "Aplikácia očakáva minimálne jeden vstupný parameter",
            "Pre manuál spustite apikáciu s parametrom -h alebo --help"
    );

    public static final String ERROR_WRONG_ARGUMENTS = Utils.composeMultilineString(
            "Zadaný nepovolený parameter alebo chybná hodnota"
    );

    public static final String MANUAL = Utils.composeMultilineString(
            "Pre čítanie a výpis hodnôt v konzole:",
            "",
            "APLIKACIA celeCislo1 celeCislo2 ... celeCisloX",
            "",
            "Pre čítanie hodnôt zo súboru a výpis do konzoly (súbor uviesť aj s príponou v prípade že ju obsahuje):",
            "V prípade že sa súbor nenachádza v rovnakom pracovnom adresári ako aplikácia, treba uviesť celú cestu",
            "",
            "APLIKACIA nazovAleboCestaKSuboru",
            "",
            "Pre čítanie aj zápis spracovaných hodnôt do súboru:",
            "",
            "APLIKACIA vstupnySubor vystupnySubor"
    );

    public static final String ERROR_NO_INPUT_FILE_FOUND = Utils.composeMultilineString(
            "Nemôžem nájsť zadaný vstupný súbor"
    );

    public static final String ERROR_FILE_READING = Utils.composeMultilineString(
            "Chyba pri čítaní súboru"
    );

    public static final String ERROR_FILE_EMPTY = Utils.composeMultilineString(
            "Súbor je prázdny"
    );

    public static final String ERROR_FILE_NOT_ALLOWED_CONTENT = Utils.composeMultilineString(
            "Súbor má nesprávny formát / obsahuje nepovolený obsah"
    );

    public static final String ERROR_WRITING_FILE = Utils.composeMultilineString(
            "Nepodarilo sa vytvorenie/zápis do súboru"
    );

    public static final String FILE_WRITE_DONE = Utils.composeMultilineString(
            "Výsledok spracovania bol zapísaný do súboru"
    );
}
