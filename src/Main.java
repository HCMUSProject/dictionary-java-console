import java.util.ArrayList;

import dictionary.CDictionary;
import dictionary.CDictionary.LanguageMode;
import dictionary.CDictionary.SortType;

public class Main {

	public static void main(String[] args) {

		CDictionary dic = new CDictionary();

		
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
		
		dic.ReadHistory();
		
		dic.PrintHistory();
		
		
	}

}
