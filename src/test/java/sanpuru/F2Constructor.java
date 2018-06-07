package sanpuru;

public enum F2Constructor implements CodeEnum<F2Constructor> {
    FERRARI("01", "フェラーリ", 2),
    FORCE_INDIA("02", "フォース・インディア", 4),
    HAAS("03", "ハース", 7),
    MCLAREN("04", "マクラーレン", 10),
    MERCEDES("05", "メルセデス", 1),
    RED_BULL("06", "レッドブル", 3),
    RENAULT("07", "ルノー", 8),
    SAUBER("08", "ザウバー", 9),
    TORO_ROSSO("09", "トロ・ロッソ", 6),
    WILLIAMS("10", "ウィリアムズ", 5);

    private String code;

    private String name;

    private int order;

    F2Constructor(String code, String name, int order) {
        this.code = code;
        this.name = name;
        this.order = order;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrder() {
        return order;
    }
}