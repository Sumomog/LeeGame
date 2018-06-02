package sanpuru;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface CodeEnum<E extends Enum<E>> {

    /**　コード値を返却する　*/
    String getCode();

    /**　名称を返却する　*/
    String getName();

    /**　[デフォルト実装] 表示の順番を返却する　*/
    default int getOrder() {
        return Integer.parseInt(getCode());
    }

    /**　[デフォルト実装] Enumに変換する　*/
    @SuppressWarnings("unchecked")
    default E toEnum() {
        return (E) this;
    }

    /**　[デフォルト実装] コード値が同一かどうかをチェックする　*/
    default boolean equalsByCode(String code) {
        return getCode().equals(code);
    }

    /**　[staticメソッド] 指定されたCodeEnumを実装したEnumを表示順にソートしたリストを返却する　*/
    static <E extends Enum<E>> List<E> getOrderedList(Class<? extends CodeEnum<E>> clazz) {
        return Arrays.stream(clazz.getEnumConstants())
                .sorted(Comparator.comparing(CodeEnum::getOrder))
                .map(CodeEnum::toEnum)
                .collect(Collectors.toList());
    }

    /**　[staticメソッド]　指定されたCodeEnumを実装したEnumの、指定されたコード値の列挙子を返却する　*/
    static <E extends Enum<E>> E getEnum(Class<? extends CodeEnum<E>> clazz, String code) {
        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> e.equalsByCode(code))
                .map(CodeEnum::toEnum)
                .findFirst()
                .orElse(null);
    }

    /**　[staticメソッド]　指定されたCodeEnumのコード値をキー、コード値に該当するCodeEnumを値に持つMapを返却する　*/
    static <E extends Enum<E>> Map<String, E> getMap(Class<? extends CodeEnum<E>> clazz) {
        return Arrays.stream(clazz.getEnumConstants())
                .collect(Collectors.toMap(CodeEnum::getCode, CodeEnum::toEnum));
    }

    /**　[staticメソッド]　指定されたCodeEnumに、指定されたコード値を持つ列挙子が存在するかチェックする　*/
    static <E extends Enum<E>> boolean hasCode(Class<? extends CodeEnum<E>> clazz, String code) {
        return Arrays.stream(clazz.getEnumConstants())
                .anyMatch(e -> e.equalsByCode(code));
    }
}