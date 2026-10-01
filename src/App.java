import java.util.Random;

/**
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {

    static final int[] TAMANHOS_TESTE_GRANDE = {
        31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000
    };

    static final int[] TAMANHOS_TESTE_MEDIO = {
        12_500, 25_000, 50_000, 100_000, 200_000
    };

    static final int[] TAMANHOS_TESTE_PEQUENO = {
        3, 6, 12, 24, 48
    };

    static final double NANO_TO_MILLI = 1.0 / 1_000_000;

    static Random aleatorio = new Random(42);

    static long operacoes;

    static int codigo1(int[] vetor) {
        int resposta = 0;
        operacoes = 0;

        for (int i = 0; i < vetor.length; i += 2) {
            resposta += vetor[i] % 2;
            operacoes++;
        }

        return resposta;
    }

    static int codigo2(int[] vetor) {
        int contador = 0;
        operacoes = 0;

        for (int k = vetor.length - 1; k > 0; k /= 2) {
            operacoes++;

            for (int i = 0; i <= k; i++) {
                contador++;
                operacoes++;
            }
        }

        return contador;
    }

    static void codigo3(int[] vetor) {
        operacoes = 0;

        for (int i = 0; i < vetor.length - 1; i++) {
            operacoes++;

            int menor = i;

            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;

                if (vetor[j] < vetor[menor]) {
                    menor = j;
                }
            }

            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    static int codigo4(int n) {
        operacoes++;

        if (n <= 2) {
            return 1;
        } else {
            return codigo4(n - 1) + codigo4(n - 2);
        }
    }

    static int[] gerarVetor(int tamanho) {
        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho / 2);
        }

        return vetor;
    }

    public static void main(String[] args) {
        System.out.println("Codigo 2 - Teste Grande");

        for (int tamanho : TAMANHOS_TESTE_GRANDE) {
            int[] vetor = gerarVetor(tamanho);

            long inicio = System.nanoTime();
            int resposta = codigo2(vetor);
            long fim = System.nanoTime();

            double tempo = (fim - inicio) * NANO_TO_MILLI;

            System.out.println();
            System.out.println("Tamanho: " + tamanho);
            System.out.println("Resposta: " + resposta);
            System.out.println("Operacoes: " + operacoes);
            System.out.println("Tempo: " + tempo + " ms");
        }
    }
}