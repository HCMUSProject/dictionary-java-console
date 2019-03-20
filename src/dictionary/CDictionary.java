package dictionary;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


import dictionary.translatewords.TranslatedWords;;

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
	
	private String _fileDictionary = "dictionary.xml";
	
	private String _fileFavorite = "favorite.xml";
	
	private String _fileHistory = "history.xml";
	
	private String _mode = "en";
	
	// luu tru tu dien theo kieu key - value
	private TreeMap<String, String> _dic = new TreeMap<String, String>();
	
	// luu lai cac tu duoc yeu thich
	private ArrayList<String> _listFavoriteWord = new ArrayList<String>();
	
	// la 1 mao luu lai cac ngay.
	// voi 1 ngay thi luu lai 1 map cac key va value
	// key la tu da tra trong ngay.
	// value la so lan tra trong ngay do
	private TreeMap<String, TranslatedWords> _ListDays = new TreeMap<String, TranslatedWords>();
	
	public CDictionary()
	{
		this._listFavoriteWord.add("b");
		this._listFavoriteWord.add("a");
		this._listFavoriteWord.add("g");
		this._listFavoriteWord.add("c");
		this._listFavoriteWord.add("d");
		this._listFavoriteWord.add("t");
	}
	
//	 ================================= Thay doi ngon ngu =================================
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
	
	
//	====================== Tra cuu va hien thi nghia cua tu ==============================
	// get du lieu vao treemap tu dien theo ngon ngu
	private boolean _getDataToDictionary() 
	{
		this._dic.clear();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			
			Document doc = documentBuilder.parse(this._fileDictionary);
			
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

	
	
//	============================== Cac tu vung yeu thich ==================================
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
			
			Document doc = documentBuilder.parse(this._fileFavorite);
			
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
	
	// ghi cac tu yeu thich xuong file xml
	public boolean WriteFavoriteWordToFile()
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document docXML = builder.newDocument();
			
			Element rootElement = docXML.createElement("Favorite");
			
			for (int i = 0; i < this._listFavoriteWord.size(); i++)
			{
				Element wordElement = docXML.createElement("Word");
				
				Text textWord = docXML.createTextNode(this._listFavoriteWord.get(i));
				
				wordElement.appendChild(textWord);
				
				rootElement.appendChild(wordElement);
			}
			
			docXML.appendChild(rootElement);
			
			// set output file
			DOMSource source = new DOMSource(docXML);
			
			File outputFile = new File(this._fileFavorite);
			
			javax.xml.transform.Result res = new StreamResult(outputFile);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			Transformer transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			transformer.transform(source, res);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public ArrayList<String> GetListFavoriteWord(){
		
//		this._getDataToFavorite();
		
		return this._listFavoriteWord;
	}


//	========================== Thong ke tra cuu cac tu ===================================
	public void SaveAField(String dataDate, String keyWord, int valueWord)
	{
		if (this._ListDays.containsKey(dataDate) == true)
		{
			// get duoc TranslatedWords ra ngoai.
			
			TranslatedWords listWord = this._ListDays.get(dataDate);
			
			listWord.PlusEntryValue(keyWord);
			
			this._ListDays.put(dataDate, listWord);
		}
		else
		{
			TranslatedWords listWord = new TranslatedWords();
			
			listWord.AddNewEntryWithValue(keyWord, valueWord);
			
			this._ListDays.put(dataDate, listWord);
		}
	}
	
	
	public boolean ReadHistory() 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			
			Document doc = documentBuilder.parse(this._fileHistory);
			
			NodeList nodeList = doc.getElementsByTagName("History");
			
			if (nodeList.getLength() != 1)
			{
				return false;
			}
			
			// node Date
			NodeList ListDate = nodeList.item(0).getChildNodes();
			
			if (ListDate.getLength() == 0)
			{
				return false;
			}
			
			for (int i = 0; i < ListDate.getLength(); i++)
			{
				Node Date = ListDate.item(i);
				
				if (Date.getNodeType() == Node.ELEMENT_NODE)
				{
					Element elDate = (Element) Date;
					
					String dataDate = elDate.getAttribute("data");
					
					// kiem tra co cac node con la cac word hay khong
					
					NodeList nodeListWord = ListDate.item(i).getChildNodes();
					
					if (nodeListWord.getLength() > 0)
					{
						for (int j = 0; j < nodeListWord.getLength(); j++)
						{
							Node Word = nodeListWord.item(j);
							
							if (Word.getNodeType() == Node.ELEMENT_NODE)
							{
								Element elWord = (Element) Word;
								
								String keyWord = elWord.getAttribute("text");
								
								int valueWord = Integer.parseInt(elWord.getTextContent());
								
								System.out.println(dataDate + " - " + keyWord + " - " + valueWord );
								
								this.SaveAField(dataDate, keyWord, valueWord);
							}
						}
					}
					
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
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	
	
//	=================  TEST FUNCTION ================================
	public void PrintHistory() {
		for (Entry<String, TranslatedWords> entry : this._ListDays.entrySet())
		{
			System.out.println(entry.getKey() + " : ");
			
			TreeMap<String, Integer> res = entry.getValue().GetListWord();
			
			for (Entry<String, Integer> et : res.entrySet())
			{
				System.out.println("- " + et.getKey() + " : " + et.getValue());
			}
		}
	}
	
	public void PrintDictionary()
	{
		this._getDataToDictionary();
		for (Entry<String, String> entry : this._dic.entrySet())
		{
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
}
