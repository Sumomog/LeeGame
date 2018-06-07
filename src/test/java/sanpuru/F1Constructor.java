package sanpuru;

public enum F1Constructor implements CodeEnum<F1Constructor> {
    FERRARI("01", "フェラーリ"),
    FORCE_INDIA("02", "フォース・インディア"),
    HAAS("03", "ハース"),
    MCLAREN("04", "マクラーレン"),
    MERCEDES("05", "メルセデス"),
    RED_BULL("06", "レッドブル"),
    RENAULT("07", "ルノー"),
    SAUBER("08", "ザウバー"),
    TORO_ROSSO("09", "トロ・ロッソ"),
    WILLIAMS("10", "ウィリアムズ");

    private String code;

    private String name;

    F1Constructor(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}