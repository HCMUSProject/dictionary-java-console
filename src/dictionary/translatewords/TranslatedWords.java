package dictionary.translatewords;

import java.util.TreeMap;

public class TranslatedWords {
	private TreeMap<String, Integer> _listWords = new TreeMap<String, Integer>();
	
	public TranslatedWords()
	{
		
	}
	
	public void PlusEntryValue(String key) {
		if (this._listWords.containsKey(key) == true)
		{
			// da co key
			// tang value
			int value = this._listWords.get(key);
			
			this._listWords.put(key, ++value);
		}
		else
		{
			// neu chua ton tai key thi set no bang 1
			// tuc la moi duoc khoi tao
			this._listWords.put(key, 1);
		}
	}
	
	public void AddNewEntryWithValue(String key, int value)
	{
		this._listWords.put(key, value);
	}
	
	public int GetValueOfKey(String key)
	{
		if (this._listWords.containsKey(key) == true)
			return this._listWords.get(key);
		else
			return -1;
	}
	
	public TreeMap<String, Integer> GetListWord()
	{
		return this._listWords;
	}
}
