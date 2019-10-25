package python;

import org.python.util.PythonInterpreter;

public class Crawling {
	private PythonInterpreter interpreter;

	public Crawling() {
		interpreter = new PythonInterpreter();

		interpreter.exec("import sys");
		interpreter.exec("sys.path.append('C:\\Users\\hjwja\\Untitled Folder')");
		interpreter.exec("sys.path.append('C:\\\\Users\\hjwja\\Anaconda3\\envs\\hyun\\python37.zip')");
		interpreter.exec("sys.path.append('C:\\Users\\hjwja\\Anaconda3\\envs\\hyun\\DLLs')");
		interpreter.exec("sys.path.append('C:\\Users\\hjwja\\Anaconda3\\envs\\hyun\\lib')");
		interpreter.exec("sys.path.append('C:\\Users\\hjwja\\Anaconda3\\envs\\hyun')");
		interpreter.exec("sys.path.append('C:\\Users\\hjwja\\Anaconda3\\envs\\hyun\\lib\\site-packages')");
		interpreter.exec(
				"sys.path.append('C:\\Users\\hjwja\\Anaconda3\\envs\\hyun\\lib\\site-packages\\IPython\\extensions')");
		interpreter.exec("sys.path.append('C:\\Users\\hjwja\\.ipython')");
//		interpreter.exec(
//				"from urllib import urlopen\r\n" + 
//				"from bs4 import BeautifulSoup\r\n" + 
//				"def crawling ():\r\n" + 
//				"    date=\"2019-09-05\"\r\n" + 
//				"    categorycode=\"200\"\r\n" + 
//				"    itemcode=\"212\"\r\n" + 
//				"    url =\"https://www.kamis.or.kr/customer/price/wholesale/item.do?action=priceinfo&regday=\"+date+\"&itemcategorycode=\"+categorycode+\"&itemcode=\"+itemcode+\"&kindcode=&productrankcode=&convert_kg_yn=N\"\r\n" + 
//				"    result = urlopen(url)\r\n" + 
//				"    html = result.read()\r\n" + 
//				"    bsObject = BeautifulSoup(html, \"html.parser\") \r\n" + 
//				"    table=bsObject.find_all(\"table\")\r\n" + 
//				"    tr=table[3].find_all(\"tr\")\r\n" + 
//				"    ta=tr[1].find_all(\"td\")\r\n" + 
//				"    print(\"평균 값:\");print(ta[1].text)\r\n" + 
//				"    return ta[1].text");
		interpreter.exec("from urllib2 import urlopen");
		interpreter.exec("import backports");
		interpreter.exec("import soupsieve");
		interpreter.exec("import bs4");

		//interpreter.exec("from bs4 import BeautifulSoup");
		//interpreter.exec("def crawling ():");
		//interpreter.exec("     print(\"asd\")");

//		interpreter.exec("     date=\"2019-09-05\"");
//		interpreter.exec("     categorycode=\"200\"");
//		interpreter.exec("     itemcode=\"212\"");
//		interpreter.exec("     url =\"https://www.kamis.or.kr/customer/price/wholesale/item.do?action=priceinfo&regday=\"+date+\"&itemcategorycode=\"+categorycode+\"&itemcode=\"+itemcode+\"&kindcode=&productrankcode=&convert_kg_yn=N\"");
//		interpreter.exec("     result = urlopen(url)");
//		interpreter.exec("     html = result.read()");
//		interpreter.exec("     bsObject = BeautifulSoup(html, \"html.parser\")");
//		interpreter.exec("     table=bsObject.find_all(\"table\")");
//		interpreter.exec("     tr=table[3].find_all(\"tr\")");
//		interpreter.exec("     ta=tr[1].find_all(\"td\")");
//		interpreter.exec("     print(\"평균 값:\");print(ta[1].text)");
//		interpreter.exec("     return ta[1].text");
		
		//interpreter.execfile("crawling.py");
		interpreter.exec("print(\"uuu\")");
	}
}
