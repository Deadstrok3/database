
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Iterator;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 


class DataBase {
	void addRecord(String filePath) throws IOException {
		String key, value,jsonInput,record;
		int flag = 0;
		int counter = 0;
		int getoutFlag=0;
		Scanner strInput = new Scanner(System.in);
		System.out.println("ENter the key");
		key = strInput.nextLine();
		System.out.println("Enter the value");
		value = strInput.nextLine();
		BufferedReader br = new BufferedReader(new FileReader(filePath +"database.txt"));
		BufferedReader br5 = new BufferedReader(new FileReader(filePath +"config.txt"));
		while((record = br.readLine()) != null) {
			if(((br5.readLine()).equals("1"))&&(counter==0))
			{
				getoutFlag=1;
				break;
			} else {
				try {
					br5.close();
					FileWriter config = new FileWriter(filePath+"/"+"config.txt");
					BufferedWriter bw5 = new BufferedWriter(config);
					bw5.write("1");
					bw5.newLine();
					bw5.flush();
					bw5.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				StringTokenizer st = new StringTokenizer(record,",");
				if(record.contains(key)) {
					flag = 1;
					System.out.println("the key already exist. This operation is not possible");
					break;
				}
				counter++;
			}
		}
		if (getoutFlag==1) {
			return;
		}
		if (flag==1) {
			return;
		}
		br.close();
		FileWriter fw =new FileWriter(filePath + "database.txt",true);
		BufferedWriter bw = new BufferedWriter(fw);


		jsonInput = strInput.nextLine();
		


		bw.write(key+","+value);
		bw.flush();
		bw.newLine();
		bw.close();
		br5.close();
		try {
			FileWriter config = new FileWriter(filePath+"/"+"config.txt");
			BufferedWriter bw5 = new BufferedWriter(config);
			bw5.write("0");
			bw5.newLine();
			bw5.flush();
			bw5.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	void viewRecords(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath +"database.txt"));

		String record;
		int counter = 0;
		int getoutFlag=0;
		System.out.println(" -----------------------------------------");
		System.out.println("key------------------------------value");
		System.out.println("------------------------------------------");
		BufferedReader br5 = new BufferedReader(new FileReader(filePath +"config.txt"));
		while((record = br.readLine()) != null) {
			if(((br5.readLine()).equals("1"))&&(counter==0))
			{
				getoutFlag=1;
				System.out.println("grvwr");
				break;
			} else {
				try {
					FileWriter config = new FileWriter(filePath+"/"+"config.txt");
					BufferedWriter bw5 = new BufferedWriter(config);
					bw5.write("1");
					bw5.newLine();
					bw5.flush();
					bw5.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				StringTokenizer st = new StringTokenizer(record,",");
				System.out.println("|    "+st.nextToken()+"  "+st.nextToken()+"   |");
				counter++;
			}

		}
		if (getoutFlag==1) {
			return;
		}
		System.out.println("|                                           |");
		System.out.println("---------------------------------------------");
		br.close();
		br5.close();
		try {
			FileWriter config = new FileWriter(filePath+"/"+"config.txt");
			BufferedWriter bw5 = new BufferedWriter(config);
			bw5.write("0");
			bw5.newLine();
			bw5.flush();
			bw5.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void deleteRecords(String filePath) throws IOException {
		Scanner strInput = new Scanner(System.in);
		String key,value,record;
		int counter = 0;
		int getoutFlag=0;

		File tempDB = new File(filePath +"database_temp.txt");
		File db = new File(filePath +"database.txt");

		BufferedReader br = new BufferedReader(new FileReader(db));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

		System.out.println("Delete employee record");
		System.out.println("enter the key");
		key = strInput.nextLine();
		BufferedReader br5 = new BufferedReader(new FileReader(filePath +"config.txt"));
		while((record = br.readLine()) != null) {
			if(((br5.readLine()).equals("1"))&&(counter==0))
			{
				getoutFlag=1;
				break;
			} else {
				try {
					FileWriter config = new FileWriter(filePath+"/"+"config.txt");
					BufferedWriter bw5 = new BufferedWriter(config);
					bw5.write("1");
					bw5.newLine();
					bw5.flush();
					bw5.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(record.contains(key))
				continue;
			bw.write(record);
			bw.flush();
			bw.newLine();
			counter++;
			}
		}
		if (getoutFlag==1) {
			return;
		}

		br.close();
		bw.close();
		db.delete();
		tempDB.renameTo(db);
		br5.close();
		try {
			FileWriter config = new FileWriter(filePath+"/"+"config.txt");
			BufferedWriter bw5 = new BufferedWriter(config);
			bw5.write("0");
			bw5.newLine();
			bw5.flush();
			bw5.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void searchRecords(String filePath) throws IOException {
		String key,value,record;
		Scanner strInput = new Scanner(System.in);
		int counter = 0;
		int getoutFlag=0;

		BufferedReader br = new BufferedReader(new FileReader(filePath +"database.txt"));
		BufferedReader br5 = new BufferedReader(new FileReader(filePath +"config.txt"));

		System.out.println("enter the key");
		key = strInput.nextLine();
		System.out.println("--------------------------------------------------");
		System.out.println("key---------------------------------------value");
		System.out.println("---------------------------------------------------");

		while((record = br.readLine()) != null) {
			if(((br5.readLine()).equals("1"))&&(counter==0))
			{
				getoutFlag=1;
				break;
			} else {
				try {
					FileWriter config = new FileWriter(filePath+"/"+"config.txt");
					BufferedWriter bw5 = new BufferedWriter(config);
					bw5.write("1");
					bw5.newLine();
					bw5.flush();
					bw5.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				StringTokenizer st = new StringTokenizer(record,",");
				if(record.contains(key)) {
					System.out.println("|"+st.nextToken()+"    "+st.nextToken()+"|");
				}
				counter++;
			}
		}
		if (getoutFlag==1) {
			return;
		}

		System.out.println("|                                                |");
		System.out.println("--------------------------------------------------");

		br.close();
		br5.close();
		try {
			FileWriter config = new FileWriter(filePath+"/"+"config.txt");
			BufferedWriter bw5 = new BufferedWriter(config);
			bw5.write("0");
			bw5.newLine();
			bw5.flush();
			bw5.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void updateRecords(String filePath) throws IOException {
		String newkey,newvalue,key,record,record2;

		File db = new File(filePath +"database.txt");
		File tempDB = new File(filePath +"database_temp.txt");

		int counter = 0;
		int getoutFlag=0;
		viewRecords(filePath);
		BufferedReader br = new BufferedReader(new FileReader(db));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

		Scanner strInput = new Scanner(System.in);

		System.out.println("update any record");
		System.out.println("enter the key");
		key= strInput.nextLine();
		System.out.println("--------------------------------------------------");
		System.out.println("key---------------------------------------value");
		System.out.println("---------------------------------------------------");

		BufferedReader br5 = new BufferedReader(new FileReader(filePath +"config.txt"));
		while((record = br.readLine()) != null) {
			if(((br5.readLine()).equals("1"))&&(counter==0))
			{
				getoutFlag=1;
				break;
			} else {
				try {
					FileWriter config = new FileWriter(filePath+"/"+"config.txt");
					BufferedWriter bw5 = new BufferedWriter(config);
					bw5.write("1");
					bw5.newLine();
					bw5.flush();
					bw5.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			StringTokenizer st = new StringTokenizer(record,",");
			if(record.contains(key)) {
				System.out.println("|    "+st.nextToken()+"   "+st.nextToken()+"    |");
			}
			counter++;
			}
		}
		if (getoutFlag==1) {
			return;
		}
			System.out.println("|                                                 ");
			System.out.println("----------------------------------------------------");
			br.close();
			System.out.println("Enter the new key");
			newkey = strInput.nextLine();
			System.out.println("Enter the new value");
			newvalue = strInput.nextLine();

			BufferedReader br2 = new BufferedReader(new FileReader(db));
			while((record2 = br2.readLine())!= null) {
				if(record2.contains(key)) {
					bw.write(newkey+","+newvalue);
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
		br5.close();
		try {
			FileWriter config = new FileWriter(filePath+"/"+"config.txt");
			BufferedWriter bw5 = new BufferedWriter(config);
			bw5.write("0");
			bw5.newLine();
			bw5.flush();
			bw5.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Scanner strInput = new Scanner(System.in);
		String choice,cont = "y";
		System.out.println("enter the path if you wish to give or press n otherwise");
		String path = strInput.nextLine();
		String filePath;
		if(path.equals("n")) {
			filePath = "database/";
			File f1 = new File("database/");
			f1.mkdirs();
			try {
				FileWriter newFile = new FileWriter(filePath + "database.txt");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			File f = new File (path);
			if (f.mkdirs()) {
				System.out.println("path created");
				filePath = path + "/";
				try {
					FileWriter newFile = new FileWriter(filePath + "database.txt");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				filePath = path + "/";
				File f1 = new File("database/");
				f1.mkdirs();
				try {
					FileWriter newFile = new FileWriter(path + "/database.txt");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			//
			try {
				Object obj = new JSONParser().parse(new FileReader(filePath+"JSONExample.json"));
				JSONObject jo = (JSONObject) obj;
				String key = (String) jo.get("key");
				String value = (String) jo.get("value");
				System.out.println("key"+key+"value"+value);
			} catch(Exception e) {
				FileWriter newfile = new FileWriter(path+"/"+"JSONExample.json");
				Object obj = new JSONParser().parse(new FileReader(filePath+"JSONExample.json"));
				JSONObject jo = (JSONObject) obj;
				String key = (String) jo.get("key");
				String value = (String) jo.get("value");
				System.out.println("key"+key+"value"+value);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		FileWriter config;
		try {
			config = new FileWriter(path+"/"+"config.txt");
			BufferedWriter br5 = new BufferedWriter(config);
			br5.write("0");
			br5.newLine();
			br5.flush();
			br5.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(cont.equalsIgnoreCase("y")) {
			System.out.println("give the key and value\n\n");

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
				new DataBase().addRecord(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (choice.equals("2")) {
			try {
				new DataBase().viewRecords(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (choice.equals("3")) {
			try {
				new DataBase().deleteRecords(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (choice.equals("4")) {
			try {
				new DataBase().searchRecords(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				new DataBase().updateRecords(filePath);
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
