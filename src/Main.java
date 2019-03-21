import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import dictionary.CDictionary;
import dictionary.CDictionary.LanguageMode;
import dictionary.CDictionary.SortType;
import dictionary.translatewords.TranslatedWords;

public class Main {

	public static void main(String[] args) throws IOException {

		CDictionary dictionary = new CDictionary();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "utf8"));
		
		int nChoose = 1;
		do 
		{
			System.out.println("---------------------------------------");
			System.out.println("|| 1. Chuyển đổi ngôn ngữ.");
			System.out.println("|| 2. Tra cứu từ.");
			System.out.println("|| 3. Danh sách các từ yêu thích.");
			System.out.println("|| 4. Thống kê tần suất tra cứu.");
			System.out.println("|| 0. Thoát.");
			System.out.println("---------------------------------------");
			// doc dua lua chon
			do {
				
				if (nChoose < 0 || nChoose > 4)
				{
					System.out.println("[Warning]: Lựa chọn không đúng!");
				}
				System.out.print("==> Lựa chọn: ");
				nChoose = Integer.parseInt(br.readLine());
			}
			while (nChoose < 0 || nChoose > 4);
			
			
			if (nChoose == 0)
			{
				return;	// thoat chuong trinh
			}
			
			switch (nChoose) {
			case 1:
				System.out.println("[+] Chuyển đổi ngôn ngữ.");
				dictionary.DisplayCurrentLanguage();
				System.out.println("1. Tiếng anh -> Tiếng việt");
				System.out.println("2. Tiếng việt -> Tiếng anh");
				
				int chooseLanguage = 1;
				
				do {
					
					if (chooseLanguage != 1 && chooseLanguage != 2)
					{
						System.out.println("[Warning]: Lựa chọn không đúng!");
					}
					System.out.print("==> Chọn ngôn ngữ:");
					chooseLanguage = Integer.parseInt(br.readLine());
				}
				while (chooseLanguage != 1 && chooseLanguage != 2);
				
				if (nChoose == 1)
				{
					dictionary.SetLanguage(LanguageMode.EN);
					System.out.println("Đã chuyển ngôn ngữ thành Tiếng Anh -> Tiếng Việt");
				}
				
				if (nChoose == 2)
				{
					dictionary.SetLanguage(LanguageMode.VI);
					System.out.println("Đã chuyển ngôn ngữ thành Tiếng Việt -> Tiếng Anh");
				}
				
				// Dung man hinh
				System.out.println("Nhấn phím bất kì để tiếp tục!");
				br.readLine();
				
				break;
			case 2:
				System.out.println("[+] Tra cứu từ.");
				dictionary.DisplayCurrentLanguage();
				
				System.out.print("Nhập từ cần tra: ");
				
				String word = "";
				
				word = br.readLine();
				
				String meaning = dictionary.GetMeaning(word);
				
				if (meaning.equals(""))
					System.out.println("[Warning] Không tìm thấy nghĩa của từ '"+ word +"'");
				else
				{
					System.out.println("Nghĩa của từ '" + word + "' là: " + meaning);
				}
				
				// Dung man hinh
				System.out.println("Nhấn phím bất kì để tiếp tục!");
				br.readLine();
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			}
			
		}
		while(true);
		
		
		
//		dic.PrintDictionary();
//		
//		dic.SetLanguage(LanguageMode.VI);
//		
//		dic.PrintDictionary();
		
//		dic.WriteFavoriteWordToFile();
		
//		dic.WriteFavoriteWordToFile();
//		
//		
//		
//		
//		
//		
//		dic.SortListFavoriteWords(SortType.AZ);
//		
//		ArrayList<String> res = dic.GetListFavoriteWord();
//	
//		System.out.println(res.size());
//		
//		for (int i = 0 ; i < res.size(); i++)
//		{
//			System.out.println(res.get(i));
//		}
//		
		
//		dic.ReadHistoryFromFile();
//		
//		dic.PrintHistory();
		
		
//		System.out.println();
		
//		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
//		
//		fmt.format(new Date());
//		
//		Date d1;
//		try {
//			d1 = fmt.parse("19/03/2019");
//			
//			Date d2;
//			try {
//				d2 = fmt.parse("22/03/2019");
//				
//				if (d1 != null && d2 != null)
//				{
//					TreeMap<Date,TranslatedWords> ret = dic.GetTranslatedWordsBetweenTwoDay(d1, d2);
//					
//					for (Entry<Date, TranslatedWords> entry : ret.entrySet())
//					{
//						System.out.println(entry.getKey() + " : ");
//						
//						TreeMap<String, Integer> res = entry.getValue().GetListWord();
//						
//						for (Entry<String, Integer> et : res.entrySet())
//						{
//							System.out.println("- " + et.getKey() + " : " + et.getValue());
//						}
//					}
//				}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		
		
//		dic.WriteHistoryToFile();
		
		
//		String startDateString = "06/27/2007";
//		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
//		Date startDate;
//		try {
//		    startDate = df.parse(startDateString);
//		    System.out.println(startDate);
//		    String newDateString = df.format(startDate);
//		    System.out.println(newDateString);
//		} catch (ParseException e) {
//		    e.printStackTrace();
//		}
		
		
	}
}
