import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.Map.Entry;

import dictionary.CDictionary;
import dictionary.CDictionary.LanguageMode;
import dictionary.CDictionary.SortType;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {

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
					System.out.println("[Lỗi]: Lựa chọn không đúng!");
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
						System.out.println("[Lỗi]: Lựa chọn không đúng!");
					}
					System.out.print("==> Chọn ngôn ngữ:");
					chooseLanguage = Integer.parseInt(br.readLine());
				}
				while (chooseLanguage != 1 && chooseLanguage != 2);
				
				if (chooseLanguage == 1)
				{
					dictionary.SetLanguage(LanguageMode.EN);
					System.out.println("[Thông báo] Đã chuyển ngôn ngữ thành Tiếng Anh -> Tiếng Việt");
				}
				
				if (chooseLanguage == 2)
				{
					dictionary.SetLanguage(LanguageMode.VI);
					System.out.println("[Thông báo] Đã chuyển ngôn ngữ thành Tiếng Việt -> Tiếng Anh");
				}
				
				// Dung man hinh
				System.out.println("==> Nhấn phím bất kì để tiếp tục!");
				br.readLine();
				
				break;
			case 2:
				System.out.println("[+] Tra cứu từ.");
				
				dictionary.DisplayCurrentLanguage();
				
				// cap nhat lai du lieu
				dictionary.GetDataToDictionary();
				
				System.out.print("==> Nhập từ cần tra: ");
				
				String word = "";
				
				word = br.readLine();
				
				String meaning = dictionary.GetMeaning(word);
				
				if (meaning.equals(""))
					System.out.println("[Thông báo] Không tìm thấy nghĩa của từ '"+ word +"'");
				else
				{
					System.out.println("[Thông báo] Nghĩa của từ '" + word + "' là: " + meaning);
					
					// luu tu vao lich su
					Date dNow = new Date();
					dictionary.AddEntryToHistory(dNow, word);
					
					// luu tu vao danh sach tu vung yeu thich
					System.out.print("==> Lưu từ '" + word +"' vào danh sách từ vựng yêu thích ? (y/n) ");
					
					String save = br.readLine();
					
					if (save.equals("y") || save.equals("Y"))
					{
						// luu lai tu
						if (dictionary.SaveFavoriteWord(word) == true)
						{
							System.out.println("[Thông báo] Lưu thành công!");
						}
						else
						{
							System.out.println("[Thông báo] Lưu thất bại. Có vẻ từ đã tồn tại.");
						}
					}
					else
					{
						System.out.println("[Thông báo] Không lưu từ '" + word + "' vào danh sách từ yêu thích.");
					}
				}
				
				// Dung man hinh
				System.out.println("==> Nhấn phím bất kì để tiếp tục!");
				br.readLine();
				break;
			case 3:
				System.out.println("[+] Danh sách các từ yêu thích.");
				System.out.println("1. A - Z");
				System.out.println("2. Z - A");
				
				int chooseOrder = 1;
				
				do {
					
					if (chooseOrder != 1 && chooseOrder != 2)
					{
						System.out.println("[Lỗi]: Lựa chọn không đúng!");
					}
					System.out.print("==> Sắp xếp theo: ");
					chooseOrder = Integer.parseInt(br.readLine());
				}
				while (chooseOrder != 1 && chooseOrder != 2);
				
				switch (chooseOrder) {
					case 1:
						dictionary.SortListFavoriteWords(SortType.AZ);
						break;
					case 2:
						dictionary.SortListFavoriteWords(SortType.ZA);
						break;
				}
				
				ArrayList<String> arrayFavoriteWord = dictionary.GetListFavoriteWord();
				System.out.println("Danh sách các từ vựng yêu thích:");
				for (int i = 0; i < arrayFavoriteWord.size(); i++)
				{
					System.out.println("\t+ " + arrayFavoriteWord.get(i));
				}
				
				// Dung man hinh
				System.out.println("==> Nhấn phím bất kì để tiếp tục!");
				br.readLine();
				break;
			case 4:
				// thong ke cac tu da tra cuu giua cac ngay
				System.out.println("[+] Thống kê tần suất tra cứu:");
				
				System.out.print("==> Ngày bắt đầu (dd/MM/yyyy) : ");
				
				String strFromDate = br.readLine();
				
				Date fromDate = null;
				
				try {
					fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(strFromDate);
				}
				catch(ParseException ex)
				{
					// ...
					System.out.println("[Lỗi] Ngày sai định dạng!");
					// Dung man hinh
					System.out.println("==> Nhấn phím bất kì để tiếp tục!");
					br.readLine();
					continue;
				}
				
				System.out.print("==> Ngày ngày kết thúc (dd/MM/yyyy) : ");
				
				String strToDate = br.readLine();
				
				Date toDate = null;
				
				try {
					toDate = new SimpleDateFormat("dd/MM/yyyy").parse(strToDate);
				}
				catch(ParseException ex)
				{
					// ...
					System.out.println("[Lỗi] Ngày sai định dạng!");
					// Dung man hinh
					System.out.println("==> Nhấn phím bất kì để tiếp tục!");
					br.readLine();
					continue;
				}
				
				if (fromDate == null || toDate == null)
				{
					System.out.println("[Lỗi] Không thể chuyển đổi ngày hoặc ngày không tồn tại!");
				}
				else
				{
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					
					TreeMap<String, Integer> countWords = dictionary.CountTranslatedWords(fromDate, toDate);
					
					System.out.println("Danh sách các từ vựng được tra từ ngày " + df.format(fromDate) + " đến " + df.format(toDate) + " :");
					
					for (Entry<String, Integer> entry : countWords.entrySet())
					{
						System.out.println("\t+ '" + entry.getKey() +"' : " + entry.getValue() + " (lần)");
					}
				}
				// Dung man hinh
				System.out.println("==> Nhấn phím bất kì để tiếp tục!");
				br.readLine();
				break;
			}
		}
		while(true);
	}
}
