package tests;

//import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.cb2xml.Cb2Xml;
import net.sf.cb2xml.util.XmlUtils;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import common.Code;

public class TstCb2xml01 {
	
	private static int COPYBOOK_COUNT1 = 112;
	private static int COPYBOOK_COUNT2 = 204;
	private static final String COPYBOOK_PREF = "cobolCopybook/cbl2xml_Test";
	private static final String XML_FILE_PREF = "xmlCopybook/cb2xml_Output";
	
	
	private final String[] COPYBOOK_LIST1 = {
			"cpyComp3aInheritence.cbl",
			"cpyComp.cbl",
			"cpyComp3.cbl",
			"cpyComp5.cbl",
			"cpyCompP.cbl",
			"cpyComp3P.cbl", 
			"cpyComp5P.cbl",
			"cpyComp3Inheritence.cbl",
			"cpyCompInheritence.cbl",
	};

	private String[] COPYBOOK_LIST2 = {
			"cpyCompSync.cbl",
			"cpyComp3Sync.cbl",
			"cpyComp5Sync.cbl",
	};

	private String[] COPYBOOK_LIST3 = {
			"cpyUnxdetail.cbl",
			"cpyOccurs.cbl",
			"cpyOccursDepending.cbl",
			"cpyOccursDependingOn21.cbl",
			"cpyOccursDependingOn29.cbl",
			"cpyOccursDependingOn42.cbl",
			"cpyBitOfEverything.cbl",
	};
	
	private String[] COPYBOOK_LIST4 = {
			"cpyRedefSize.cbl",
			"cpyRedefSize01.cbl",
			"cpyRedefSize02.cbl",
			"cpyRedefSize03.cbl",
			"cpyRedefSize08.cbl",
			"cpyRedefSize11.cbl",
			"cpyRedefSize12.cbl",
			"cpyRedefSize14.cbl",
			"cpyRedefSize15.cbl",
			"cpyRedefSize21.cbl",
			"cpyRedefSize23.cbl",
			"cpyRedefSize24.cbl",
			"cpyRedefSize25.cbl",
			"cpyRedefSize31.cbl",
			"cpyValueContinuation1.cbl",
			"cpyValueContinuation2.cbl",
			"cpyValueContinuation.cbl",
			"cpyMsg.cbl",
			"cpyHexValue.cbl",
			"cpyPicNG01.cbl",
			"cpyPicNG02.cbl",
			"cpyPicNG03.cbl",
	};

	
	@Test
	public void test1() throws IOException, SAXException, ParserConfigurationException {
		doTest(101, COPYBOOK_COUNT1);
	}
	
	@Test
	public void test2() throws IOException, SAXException, ParserConfigurationException {
		doTest(201, COPYBOOK_COUNT2);
	}

	private void doTest(int start, int end) throws IOException, SAXException, ParserConfigurationException{
		String cblFilename, xmlFilename;
		for (int i = start; i <= end; i++) {
			System.out.println("Test: " + i);
			cblFilename = Code.getFullName(COPYBOOK_PREF + i + ".cbl");
			
			Document doc = Cb2Xml.convertToXMLDOM(new File(cblFilename));
			
			System.out.println(i + " " + cblFilename + ":");
			System.out.println(XmlUtils.domToString(doc));
			System.out.println();
			
//			Writer w = new OutputStreamWriter(new FileOutputStream("G:\\Temp\\cb2xml\\xmlCopybooks\\cb2xml_Output" + i + ".xml") , "utf-8");
//			w.write(XmlUtils.domToString(doc).toString());
//			w.close();
		
			
			xmlFilename = Code.getFullName(XML_FILE_PREF + i + ".xml");
			common.Code.compare("File: " + cblFilename, xmlFilename, doc);
		}
		//fail("Not yet implemented");
	}

	@Test
	public void testArray1() throws IOException, SAXException, ParserConfigurationException {
		tstArray(COPYBOOK_LIST1);
	}

	@Test
	public void testArray2() throws IOException, SAXException, ParserConfigurationException {
		tstArray(COPYBOOK_LIST2);
	}

	@Test
	public void testArray3() throws IOException, SAXException, ParserConfigurationException {
		tstArray(COPYBOOK_LIST3);
	}


	@Test
	public void testArray4() throws IOException, SAXException, ParserConfigurationException {
		tstArray(COPYBOOK_LIST4);
	}

	public void tstArray(String[] copybooks) throws IOException, SAXException, ParserConfigurationException {
		String cblFilename, xmlFilename;
		for (String c : copybooks) {
			xmlFilename = "xmlCpy" + c.substring(3, c.length() - 3) + "Xml";
			System.out.println("Test: " + c + " " + xmlFilename);
			cblFilename = Code.getFullName("cobolCopybook/" + c);
			
			Document doc = Cb2Xml.convertToXMLDOM(new File(cblFilename));
			
			System.out.println(c + " --> " + xmlFilename + ":");
			System.out.println(XmlUtils.domToString(doc));
			System.out.println();
		
//			Writer w = new OutputStreamWriter(new FileOutputStream("G:\\Temp\\cb2xml\\xmlCopybooks\\" + xmlFilename) , "utf-8");
//			w.write(XmlUtils.domToString(doc).toString());
//			w.close();
			
			xmlFilename = Code.getFullName("xmlCopybook/" + xmlFilename);
			common.Code.compare("File: " + cblFilename, xmlFilename, doc);
			
		}
		//fail("Not yet implemented");
	}

}
