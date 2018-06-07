package sanpuru;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

import org.junit.Test;

/**
 * http://d.hatena.ne.jp/Kazuhira/20130309/1362838458
 */
public class ParameterizedTypeExample {
	@Test
	void test01() {
		main(null);
	}
    public static void main(String[] args) {
        try {
            printf("-------------------- Print ParameterizedClass --------------------%n");
            printGenerics(ParameterizedClass.class);
            printf("-------------------- Print ParameterizedClass --------------------%n");
            printf("%n%n");

            printf("-------------------- Print nonGeneric --------------------%n");
            printGenerics(ParameterizedClass.class.getField("nonGeneric"));
            printf("-------------------- Print nonGeneric --------------------%n");
            printf("%n%n");

            printf("-------------------- Print field --------------------%n");
            //printf("Field#getType => %s%n",
            //       ParameterizedClass.class.getField("field").getType().getName());
            printGenerics(ParameterizedClass.class.getField("field"));
            printf("-------------------- Print field --------------------%n");
            printf("%n%n");

            printf("-------------------- Print array --------------------%n");
            printGenerics(ParameterizedClass.class.getField("array"));
            printf("-------------------- Print array --------------------%n");
            printf("%n%n");

            printf("-------------------- Print strings --------------------%n");
            printGenerics(ParameterizedClass.class.getField("strings"));
            printf("-------------------- Print strings --------------------%n");
            printf("%n%n");

            printf("-------------------- Print list --------------------%n");
            printGenerics(ParameterizedClass.class.getField("list"));
            printf("-------------------- Print list --------------------%n");
            printf("%n%n");

            printf("-------------------- Print listArray --------------------%n");
            printGenerics(ParameterizedClass.class.getField("listArray"));
            printf("-------------------- Print listArray --------------------%n");
            printf("%n%n");

            printf("-------------------- Print wildUnderNumbers --------------------%n");
            printGenerics(ParameterizedClass.class.getField("wildUnderNumbers"));
            printf("-------------------- Print wildUnderNumbers --------------------%n");
            printf("%n%n");

            printf("-------------------- Print wildUpperNumbers --------------------%n");
            printGenerics(ParameterizedClass.class.getField("wildUpperNumbers"));
            printf("-------------------- Print wildUpperNumbers --------------------%n");
            printf("%n%n");

            printf("-------------------- Print m1 --------------------%n");
            printGenerics(ParameterizedClass.class.getMethod("m1", Object.class, Object.class));
            printf("-------------------- Print m1 --------------------%n");
            printf("%n%n");

            printf("-------------------- Print m2 --------------------%n");
            printGenerics(ParameterizedClass.class.getMethod("m2"));
            printf("-------------------- Print m2 --------------------%n");
            printf("%n%n");

            printf("-------------------- Print m3 --------------------%n");
            printGenerics(ParameterizedClass.class.getMethod("m3"));
            printf("-------------------- Print m3 --------------------%n");
            printf("%n%n");

            printf("-------------------- Print m4 --------------------%n");
            printGenerics(ParameterizedClass.class.getMethod("m4", List.class));
            printf("-------------------- Print m4 --------------------%n");
            printf("%n%n");

            printf("-------------------- Print NonGenericClass --------------------%n");
            printGenerics(NonGeneric.class);
            printf("-------------------- Print NonGenericClass --------------------%n");
            printf("%n%n");

            printf("-------------------- Print ActualClass --------------------%n");
            printGenerics(Actual.class);
            printf("-------------------- Print ActualClass --------------------%n");
            printf("%n%n");

            printf("-------------------- Print ParameterizedClass2 --------------------%n");
            printGenerics(ParameterizedClass2.class);
            printf("-------------------- Print ParameterizedClass2 --------------------%n");
            printf("%n%n");
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void printGenerics(Object target) {
        if (target instanceof Class) {
            printf("<<<<< Class#getTypeParameterTypes >>>>>%n");
            TypeVariable[] tvs = ((Class<?>)target).getTypeParameters();
            if (tvs.length > 0) {
                for (TypeVariable tv : tvs) {
                    dispatch(tv);
                }
            } else {
                printf("No Parameter Class[%s]%n", target);
            }
        } else if (target instanceof Field) {
           printf("<<<<< Field#getGenericTypes >>>>>%n");
            dispatch(((Field)target).getGenericType());
        } else if (target instanceof Method) {
            Method m = (Method)target;
            Type[] pts = m.getGenericParameterTypes();
            printf("<<<<< Method#getGenericParameterTypes >>>>>%n");
            for (Type pt : pts) {
                dispatch(pt);
            }

            Type rts = m.getGenericReturnType();
            printf("<<<<< Method#getGenericReturnType >>>>>%n");
            dispatch(rts);

            Type[] ets = m.getGenericExceptionTypes();
            printf("<<<<< Method#getGenericExceptionTypes >>>>>%n");
            for (Type et : ets) {
                dispatch(et);
            }
        } else if (target instanceof Constructor) {
            Constructor c = (Constructor)target;
            Type[] pts = c.getGenericParameterTypes();
            printf("<<<<< Constructor#getGenericParameterTypes >>>>>%n");
            for (Type pt : pts) {
                dispatch(pt);
            }

            Type[] ets = c.getGenericExceptionTypes();
            printf("Constructor#getGenericExceptionTypes >>>>>%n");
            for (Type et : ets) {
                dispatch(et);
            }
        } else {
            printf("Unknown Type... [%s]%n", target);
        }
    }

    private static void dispatch(Type type) {
        if (type instanceof TypeVariable) {
            printTypeVariables((TypeVariable<?>)type);
        } else if (type instanceof ParameterizedType) {
            printParameterizedType((ParameterizedType)type);
        } else if (type instanceof WildcardType) {
            printWildcardType((WildcardType)type);
        } else if (type instanceof GenericArrayType) {
            printGenericArrayType((GenericArrayType)type);
        } else if (type instanceof Class) {
            printf("Class class => %s%n", type);
        } else {
            printf("Unkown Type... => %s%n", type);
        }
    }

    private static void printTypeVariables(TypeVariable<?> tv) {
        printf("TypeVariable#getName => %s%n", tv.getName());
        printf("TypeVariable#getGenericDeclaration => %s%n", tv.getGenericDeclaration());
        for (Type t : tv.getBounds()) {
            printf("TypeVariable#getBounds ---->%n");
            dispatch(t);
        }
    }

    private static void printParameterizedType(ParameterizedType pt) {
        printf("ParameterizedType#getOwnerType => %s%n", pt.getOwnerType());
        printf("ParameterizedType#getRawType => %s%n", pt.getRawType());
        for (Type t : pt.getActualTypeArguments()) {
            printf("ParameterizedType#getActualTypeArguments ---->%n");
            dispatch(t);
        }
    }

    private static void printWildcardType(WildcardType wt) {
        for (Type t : wt.getLowerBounds()) {
            printf("WildcardType#getLowerBounds ---->%n");
            dispatch(t);
        }
        for (Type t : wt.getUpperBounds()) {
            printf("WildcardType#getUpperBounds ---->%n");
            dispatch(t);
        }
    }

    private static void printGenericArrayType(GenericArrayType gat) {
        printf("GenericArrayType#getGenericComponentType ---->%n");
        dispatch(gat.getGenericComponentType());
    }

    private static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}

class ParameterizedClass<T> {
    public String nonGeneric;
    public T field;
    public T[] array;
    public List<String> strings;
    public List<T> list;
    public List<T>[] listArray;
    public List<? extends Number> wildUnderNumbers;
    public List<? super Number> wildUpperNumbers;

    public <A, B> void m1(A a, B b) { }
    public <A extends Number> A m2() { return null; };
    public <A extends java.io.IOException, B extends RuntimeException> void m3() throws A, B { }
    public <A> void m4(List<? extends A> list) { }
}

class NonGeneric extends ParameterizedClass<Object> { }
class Actual<String> extends ParameterizedClass<String> { }
class ParameterizedClass2<T extends Number> { }