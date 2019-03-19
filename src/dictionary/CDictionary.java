package dictionary;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CDictionary {
	public static enum LanguageMode{
		EN,
		VI
	}
	
	public static enum SortType{
		AZ,
		ZA
	}
	// mode = en : en - vi
	// mode = vi : vi - en
	
	private String fileDictionary = "dictionary.xml";
	
	private String fileFavorite = "favorite.xml";
	
	private String _mode = "en";
	
	// luu tru tu dien theo kieu key - value
	private TreeMap<String, String> _dic = new TreeMap<String, String>();
	
	// luu lai cac tu duoc yeu thich
	private ArrayList<String> _listFavoriteWord = new ArrayList<String>();	
	
	public CDictionary()
	{
		this._listFavoriteWord.add("b");
		this._listFavoriteWord.add("a");
		this._listFavoriteWord.add("g");
		this._listFavoriteWord.add("c");
		this._listFavoriteWord.add("d");
		this._listFavoriteWord.add("t");
	}
	
	// chon ngon ngu tra cuu
	public void SetLanguage(LanguageMode mode) {
		switch (mode) {
		case EN:
			this._mode = "en";
			break;
		case VI:
			this._mode = "vi";
			break;
		default:
			this._mode = "en";
			break;
		}
		
		// cap nhat lai SortedMap
		
		this._getDataToDictionary();
	}

	// get du lieu vao treemap tu dien theo ngon ngu
	private boolean _getDataToDictionary() 
	{
		this._dic.clear();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			
			Document doc = documentBuilder.parse(this.fileDictionary);
			
			NodeList nodeList = doc.getElementsByTagName(this._mode);
			
			if (nodeList.getLength() != 1)
			{
				return false;
			}
			
			NodeList ListWord = nodeList.item(0).getChildNodes();
			
			if (ListWord.getLength() == 0)
			{
				return false;
			}
			
			for (int i = 0; i < ListWord.getLength(); i++)
			{
				Node Word = ListWord.item(i);
				
				if (Word.getNodeType() == Node.ELEMENT_NODE)
				{
					Element elWord = (Element) Word;
					
					String key = elWord.getAttribute("data");
					
					String value = elWord.getTextContent();
					
					// luu du lieu vao TreeMap
					
					this._dic.put(key, value);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	// tim kiem nghia cua tu
	public String GetMeaning(String key)
	{
		String res = "";
		
		if (this._dic.containsKey(key) == true)
		{
			res = this._dic.get(key);
		}
		
		return res;
	}

	// sap xep danh sach cac tu vung ua thich theo SortType
	public void SortListFavoriteWords(SortType st)
	{
		Comparator<String> compare = new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};
		
		switch (st) {
		case AZ:
			Collections.sort(this._listFavoriteWord, compare);
			break;
		case ZA:
			Collections.sort(this._listFavoriteWord, compare.reversed());
			break;
		}
	}
	
	// them tu yeu thich vao tu dien
	public boolean SaveFavoriteWord(String Word)
	{
		if (this._listFavoriteWord.contains(Word) == true)
		{
			return false;
		}
		
		return this._listFavoriteWord.add(Word);
	}
	
	// doc tu file danh sach tu yeu thich
	private boolean _getDataToFavorite()
	{
		this._listFavoriteWord.clear();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try 
		{
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			
			Document doc = documentBuilder.parse(this.fileFavorite);
			
			NodeList nodeList = doc.getElementsByTagName("Favorite");
			
			if (nodeList.getLength() != 1)
			{
				return false;
			}
			
			NodeList ListWord = nodeList.item(0).getChildNodes();
			
			if (ListWord.getLength() == 0)
			{
				return false;
			}
			
			for (int i = 0; i < ListWord.getLength(); i++)
			{
				Node word = ListWord.item(i);
				
				if (word.getNodeType() == Node.ELEMENT_NODE)
				{
					Element elWord = (Element) word;
					
					String value = elWord.getTextContent();

					if (this._listFavoriteWord.contains(value) == true)
					{
						continue;
					}

					this._listFavoriteWord.add(value);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// ghi 
	
//	=================  TEST FUNCTION ================================
	public void PrintDictionary()
	{
		this._getDataToDictionary();
		for (Entry<String, String> entry : this._dic.entrySet())
		{
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
}
