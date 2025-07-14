package org.sam;

import java.util.function.BiFunction;

public enum Operaciones {
    SUMA((a, b) -> a + b),
    RESTA((a, b) -> a - b),
    MULTIPLICACION((a, b) -> a * b),
    DIVISION((a, b) -> b != 0 ? a / b : 0);

    /* BiFunction representa una funcion que acepta dos parametros, y retorna un parametro
    *
    *   @FunctionalInterface
    *   public interface BiFunction<T, U, R> {
    *        R apply(T t, U u);
    *   }
    * T → Tipo del primer parámetro de entrada.
    * U → Tipo del segundo parámetro de entrada.
    * R → Tipo del valor de retorno.
    * */
    private final BiFunction<Integer, Integer, Integer> operacion;

    // Constructor de Enum, Acepta un BiFunction
    Operaciones(BiFunction<Integer, Integer, Integer> operacion) {
        this.operacion = operacion;
    }

    // La forma de invocar la funcion sería asi
    // Operaciones.SUMA.calcular(numero1, numero2);
    public int calcular(int a, int b) {
        return operacion.apply(a, b);
    }
}
