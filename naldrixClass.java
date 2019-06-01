import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

class MyClass {
	void addRecord() throws IOException {
		FileWriter fw =new FileWriter("naldrix_db.txt",true);
		BufferedWriter bw = new BufferedWriter(fw);
		Scanner strInput = new Scanner(System.in);

		String name, address;

		System.out.println("ENter the name");
		name = strInput.nextLine();
		System.out.println("Enter the address");
		address = strInput.nextLine();

		bw.write(name+","+address);
		bw.flush();
		bw.newLine();
		bw.close();
	}

	void viewRecords() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("naldrix_db.txt"));

		String record;
		System.out.println(" -----------------------------------------");
		System.out.println("name------------------------------address");
		System.out.println("------------------------------------------");
		while((record = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(record,",");
			System.out.println("|    "+st.nextToken()+"  "+st.nextToken()+"   |");
		}
		System.out.println("|                                           |");
		System.out.println("---------------------------------------------");
		br.close();
	}

	void deleteRecords() throws IOException {
		Scanner strInput = new Scanner(System.in);
		String name,address,record;

		File tempDB = new File("naldrix_db_temp.txt");
		File db = new File("naldrix_db.txt");

		BufferedReader br = new BufferedReader(new FileReader(db));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

		System.out.println("Delete employee record");
		System.out.println("enter the name");
		name = strInput.nextLine();
		while((record = br.readLine()) != null) {
			if(record.contains(name))
				continue;
			bw.write(record);
			bw.flush();
			bw.newLine();
		}

		br.close();
		bw.close();
		db.delete();
		tempDB.renameTo(db);
	}

	void searchRecords() throws IOException {
		String name,address,record;
		Scanner strInput = new Scanner(System.in);

		BufferedReader br = new BufferedReader(new FileReader("naldrix_db.txt"));

		System.out.println("enter the name");
		name = strInput.nextLine();
		System.out.println("--------------------------------------------------");
		System.out.println("name---------------------------------------address");
		System.out.println("---------------------------------------------------");

		while((record = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(record,",");
			if(record.contains(name)) {
				System.out.println("|"+st.nextToken()+"    "+st.nextToken()+"|");
			}
		}

		System.out.println("|                                                |");
		System.out.println("--------------------------------------------------");

		br.close();
	}

	void updateRecords() throws IOException {
		String newName,newAddress,name,record,record2;

		File db = new File("naldrix_db.txt");
		File tempDB = new File("naldrix_db_temp");

		viewRecords();
		BufferedReader br = new BufferedReader(new FileReader(db));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

		Scanner strInput = new Scanner(System.in);

		System.out.println("update any record");
		System.out.println("enter the name");
		name= strInput.nextLine();
		System.out.println("--------------------------------------------------");
		System.out.println("name---------------------------------------address");
		System.out.println("---------------------------------------------------");

		while((record = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(record,",");
			if(record.contains(name)) {
				System.out.println("|    "+st.nextToken()+"   "+st.nextToken()+"    |");
			}
		}
			System.out.println("|                                                 ");
			System.out.println("----------------------------------------------------");
			br.close();
			System.out.println("Enter the new name");
			newName = strInput.nextLine();
			System.out.println("Enter the new Address");
			newAddress = strInput.nextLine();

			BufferedReader br2 = new BufferedReader(new FileReader(db));
			while((record2 = br2.readLine())!= null) {
				if(record2.contains(name)) {
					bw.write(newName+","+newAddress);
				} else {
					bw.write(record2);
				}
				bw.flush();
				bw.newLine();
			}
			bw.close();
			br2.close();
		db.delete();
		boolean success = tempDB.renameTo(db);
		System.out.print(success);
	}

	public static void main(String args[]) {
		Scanner strInput = new Scanner(System.in);
		String choice,cont = "y";
		while(cont.equalsIgnoreCase("y")) {
			System.out.println("give the name and address\n\n");

			System.out.println("1==> Add new record\n");
			System.out.println("2 ==> View Records\n");
			System.out.println("3 ==> Delete record\n");
			System.out.println("4 ===> Search records");
			System.out.println("5 ====> update Records");
		System.out.print("\n\n");
		System.out.println("Enter your choice");
		choice = strInput.nextLine();

		if(choice.equals("1")) {
			try {
				new MyClass().addRecord();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (choice.equals("2")) {
			try {
				new MyClass().viewRecords();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (choice.equals("3")) {
			try {
				new MyClass().deleteRecords();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (choice.equals("4")) {
			try {
				new MyClass().searchRecords();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				new MyClass().updateRecords();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Do you want to continue? y/n");
		cont = strInput.nextLine();
	}
	}
}
