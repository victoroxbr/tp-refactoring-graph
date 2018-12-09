package org.acme.graph.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.junit.Assert;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

import com.vividsolutions.jts.geom.Geometry;

/**
 * 
 * Classe de démonstration pour la lecture d'un shapefile à l'aide de Geotools
 * 
 * @author MBorne
 *
 */
public class GeotoolsTest {

	private File getResourceFile(String name) {
		URL url = getClass().getResource(name);
		Assert.assertNotNull("resource not found : "+name, url);
		File file = new File(url.getPath()) ;
		return file;
	}

	@Test
	public void testReadShapefile() throws IOException {
		File file = getResourceFile("/route500/idf/troncon_route.shp");
		Assert.assertTrue(file.exists());
		
        Map<String, Object> map = new HashMap<>();
        map.put("url", file.toURI().toURL());

        DataStore dataStore = DataStoreFinder.getDataStore(map);
        String typeName = dataStore.getTypeNames()[0];

        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        Filter filter = Filter.INCLUDE;

        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
        int count = 0;
        try (FeatureIterator<SimpleFeature> features = collection.features()) {
            while (features.hasNext()) {
                SimpleFeature feature = features.next();
                
                String   id       = feature.getID();
                String   sens     = (String)feature.getAttribute("SENS");
                Geometry geometry = (Geometry)feature.getDefaultGeometryProperty().getValue();
                //System.out.println(id+" "+sens+" "+geometry);
                count++;
            }
        }
        Assert.assertEquals(24768,count);
	}
	
}
