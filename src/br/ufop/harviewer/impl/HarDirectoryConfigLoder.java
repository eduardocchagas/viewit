package br.ufop.harviewer.impl;

import java.io.FileInputStream;
import java.io.InputStream;

import br.ufop.harviewer.model.HarDirectoryConfig;
import com.thoughtworks.xstream.XStream;

public class HarDirectoryConfigLoder {

	public static HarDirectoryConfig loadHarDirectoryConfig(String configXmlPath) {
		try {

			InputStream in = new FileInputStream(configXmlPath);
			XStream xstream = new XStream();

			xstream.processAnnotations(HarDirectoryConfig.class);
			HarDirectoryConfig config = (HarDirectoryConfig) xstream
					.fromXML(in);
			return config;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
