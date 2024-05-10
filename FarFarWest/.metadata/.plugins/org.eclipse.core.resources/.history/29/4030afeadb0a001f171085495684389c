package Tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Test1 {

	@Test
	void test() {
		Calculadora tester = new Calculadora();
		assertAll(() -> assertEquals(5.0f, tester.sumar(2F, 3F)), () -> assertEquals(1.0f, tester.restar(3F, 2F)),
				() -> assertEquals(10.0f, tester.multiplicar(2F, 5F)),
				() -> assertEquals(0.0f, tester.dividir(5F, 0F)));
		System.out.println("test exitoso");
	}

	@Test
	void test2() {
		Calculadora tester = new Calculadora();
		assertAll(() -> assertEquals(7.0f, tester.sumar(4F, 3F)), () -> assertEquals(5.5f, tester.restar(8F, 2.5F)),
				() -> assertEquals(7.5f, tester.multiplicar(2.5F, 3F)),
				() -> assertEquals(2.5f, tester.dividir(5F, 2F)));
		System.out.println("test2 exitoso");
	}

	@Test
	void test3() {
		Calculadora tester = new Calculadora();
		assertAll(() -> assertEquals(-1.0f, tester.sumar(-4F, 3F)), () -> assertEquals(10.5f, tester.restar(8F, -2.5F)),
				() -> assertEquals(7.5f, tester.multiplicar(-2.5F, -3F)),
				() -> assertEquals(-2.5f, tester.dividir(5F, -2F)));
		System.out.println("test3 exitoso");
	}

	@Test
	void test4() {
		Calculadora tester = new Calculadora();
		assertAll(() -> assertEquals(-4f, tester.sumar(-4F, 0F)), () -> assertEquals(8f, tester.restar(8F, 0F)),
				() -> assertEquals(-0f, tester.multiplicar(-2.5F, 0F)),
				() -> assertEquals(2.5f, tester.dividir(-5F, -2F)));
		System.out.println("test4 exitoso");
	}

	@Test
	void test5() {
		Calculadora tester = new Calculadora();
		assertAll(() -> assertEquals(-4f, tester.sumar(-4F, 0F)), () -> assertEquals(8f, tester.restar(8F, 0F)),
				() -> assertEquals(0f, tester.multiplicar(-2.5F, 0F)),
				() -> assertEquals(2.5f, tester.dividir(-5F, -2F)));
		System.out.println("test5 exitoso");
	}

}
