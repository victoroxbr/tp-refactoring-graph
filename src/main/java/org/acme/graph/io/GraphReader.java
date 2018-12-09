package org.acme.graph.io;

import java.io.File;

import org.acme.graph.model.Graph;

/**
 * 
 * Classe utilitaire pour le chargement des graphes avec détection automatique
 * du format
 * 
 * @author MBorne
 *
 */
public class GraphReader {

	/**
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Graph read(File file) throws Exception {
		String fileName = file.getName().toLowerCase();
		if (fileName.endsWith(".xml")) {
			return XmlGraphReader.read(file);
		} else if (fileName.endsWith(".shp")) {
			return ShpGraphReader.read(file);
		} else {
			throw new RuntimeException("format non supporté (.shp ou .xml attendu)");
		}
	}

}
