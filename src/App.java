import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        App app = new App(); 
        // Questoes da tarefa
        // 1.
        app.soma(); 

        // 2. 
        app.isFibonacci();

        // 3.
        app.faturamentoDistribuidora();

        // 4.
        app.calcularFaturamentoPorEstados();


    }

    public void soma() {
        int INDICE = 13, SOMA = 0, K = 0;

        while (K < INDICE) {
            K = K + 1;
            SOMA = SOMA + K;
        }

        System.out.println("O valor final da variável SOMA é: " + SOMA);
    }

    public boolean isFibonacci() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe um número: ");
        int num = scanner.nextInt();

        int a = 0, b = 1;

        if (num == a || num == b) {
            return true;
        }

        int c = a + b;

        while (c <= num) {
            if (c == num) {
                return true;
            }

            a = b;
            b = c;
            c = a + b;
        }

        return false;
    }

    public void faturamentoDistribuidora() {
        try {
            // Lê o arquivo JSON
            String filePath = "dados.json"; // Substitua pelo caminho do arquivo JSON
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray faturamentoArray = new JSONArray(content);

            double menorValor = Double.MAX_VALUE;
            double maiorValor = Double.MIN_VALUE;
            double soma = 0;
            int diasComFaturamento = 0;

            // Calcula menor, maior e soma ignorando dias sem faturamento
            for (int i = 0; i < faturamentoArray.length(); i++) {
                JSONObject dia = faturamentoArray.getJSONObject(i);
                double valor = dia.getDouble("valor");

                if (valor > 0) { // Ignorar dias sem faturamento
                    if (valor < menorValor) {
                        menorValor = valor;
                    }
                    if (valor > maiorValor) {
                        maiorValor = valor;
                    }
                    soma += valor;
                    diasComFaturamento++;
                }
            }

            // Calcula média mensal
            double media = soma / diasComFaturamento;

            // Calcula dias com faturamento acima da média
            int diasAcimaMedia = 0;
            for (int i = 0; i < faturamentoArray.length(); i++) {
                JSONObject dia = faturamentoArray.getJSONObject(i);
                double valor = dia.getDouble("valor");

                if (valor > media) {
                    diasAcimaMedia++;
                }
            }

            // Exibe os resultados
            System.out.println("Menor valor de faturamento: " + menorValor);
            System.out.println("Maior valor de faturamento: " + maiorValor);
            System.out.println("Número de dias acima da média mensal: " + diasAcimaMedia);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcularFaturamentoPorEstados() {
        // Lista de faturamentos por estado
        List<FaturamentoPorEstado> estados = new ArrayList<>();
        estados.add(new FaturamentoPorEstado("SP", 67836.43));
        estados.add(new FaturamentoPorEstado("RJ", 36678.66));
        estados.add(new FaturamentoPorEstado("MG", 29229.88));
        estados.add(new FaturamentoPorEstado("ES", 27165.48));
        estados.add(new FaturamentoPorEstado("Outros", 19849.53));

        // Calculando o faturamento total
        double faturamentoTotal = 0;
        for (FaturamentoPorEstado estado : estados) {
            faturamentoTotal += estado.getFaturamento();
        }

        // Exibindo a porcentagem de cada estado
        System.out.println("Faturamento por estado (em porcentagem):");
        for (FaturamentoPorEstado estado : estados) {
            double percentual = (estado.getFaturamento() / faturamentoTotal) * 100;
            System.out.printf("%s: %.2f%%\n", estado.getEstado(), percentual);
        }
    }

    class FaturamentoPorEstado {
        private String estado;
        private double faturamento;
    
        public FaturamentoPorEstado(String estado, double faturamento) {
            this.estado = estado;
            this.faturamento = faturamento;
        }
    
        public String getEstado() {
            return estado;
        }
    
        public void setEstado(String estado) {
            this.estado = estado;
        }
    
        public double getFaturamento() {
            return faturamento;
        }
    
        public void setFaturamento(double faturamento) {
            this.faturamento = faturamento;
        }
    }

}
