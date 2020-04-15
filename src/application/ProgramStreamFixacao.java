package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class ProgramStreamFixacao {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);  
		
		System.out.println();
		System.out.println("===========Exerc�cio fixacao sobre Stream======================");
		System.out.println();
		System.out.println("Fazer um programa para ler os dados (nome, email e sal�rio) de funcion�rios de um arquivo .csv."); 
		System.out.println("Em seguida mostrar, em ordem alfab�tica, o email dos funcion�rios cujo sal�rio seja superior a");
		System.out.println("um dado valor fornecido pelo usu�rio. Mostrar tamb�m a soma dos sal�rios dos funcion�rios cujo");
		System.out.println("nome come�a com a letra 'M'.");
		System.out.println();	
		System.out.println("Exemplo dados arquivo .CSV");	
		System.out.println("Mauricio boy,oitavo@email.com,5723.57");
		System.out.println("Michele fachineira,setimo@email.com,1103.91");
		System.out.println("Monica mulher do dono,sexto@email.com,1290.52");
		System.out.println("Maria secretaria,quinto@email.com,850.33");
		System.out.println("Giovani auxiliar,quarto@email.com,1680.90");
		System.out.println("Antonio meia boca,terceiro@email.com,1150.50");
		System.out.println("Mauro Subordinado,segundo@email.com,750.00");
		System.out.println("Joao Ch�o de F�brica,primero@email.com,900.00");		
		System.out.println();		
		
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter to filter minimum salary: ");
		Double minSalary = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
								
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > minSalary)  //filtra por valores maiores que o valor de entrada
					.map(p -> p.getEmail()).sorted() //ordenar de forma crescente
					.collect(Collectors.toList());
			
			System.out.print("Email of people whose salary is more than " + String.format("%.2f", minSalary));
			emails.forEach(System.out::println); 
			
			double soma = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M') 
					.map(p -> p.getSalary())				 
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println();	
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", soma));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
