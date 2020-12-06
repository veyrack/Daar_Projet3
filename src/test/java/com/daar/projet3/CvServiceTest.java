package com.daar.projet3;

import org.junit.runner.RunWith;
import com.daar.projet3.models.CV;
import com.daar.projet3.services.CvService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Projet3Application.class)
public class CvServiceTest {

    @Autowired
    private CvService cvService;

    @Autowired
    private ElasticsearchRestTemplate esTemplate;

    @Before
    public void before() {

        //esTemplate.delete(CV.class);
        if (esTemplate.indexOps(CV.class).exists()){
            esTemplate.indexOps(CV.class).delete();
        }
        esTemplate.indexOps(CV.class).create();
    }

    @Test
    public void testSave() {

        List<String> l = new ArrayList<>();
        l.add("Java");
        l.add("C");
        l.add("Haskell");
        CV cv = new CV("1001", "Jean", "Lin",23,"a@b.fr","01", l,l);
        CV testCv = cvService.save(cv);

        assertNotNull(testCv.getId());
        assertEquals(testCv.getPrenom(), cv.getPrenom());
        assertEquals(testCv.getNom(), cv.getNom());
        assertEquals(testCv.getMotsCles(), cv.getMotsCles());
    }

    @Test
    public void testFindOne() {
        List<String> l = new ArrayList<>();
        l.add("Java");
        l.add("C");
        l.add("Haskell");
        CV cv = new CV("1001", "Jean", "Lin",23,"a@b.fr","01",l, l);
        cvService.save(cv);

        CV testCV = cvService.findOne(cv.getId());

        assertNotNull(testCV.getId());
        assertEquals(testCV.getPrenom(), cv.getPrenom());
        assertEquals(testCV.getNom(), cv.getNom());
        assertEquals(testCV.getMotsCles(), cv.getMotsCles());
    }

    @Test
    public void testFindByMot() {
        List<String> l = new ArrayList<>();
        l.add("Java");
        l.add("C");
        l.add("Haskell");
        CV cv = new CV("1001", "Jean", "Lin",23,"a@b.fr","01",l, l);
        cvService.save(cv);

        List<CV> byFilter = cvService.findByMotsCles("Java");
        System.out.println(byFilter.toString());
        assertThat(byFilter.size(), is(1));
    }

    @Test
    public void testDelete() {
        List<String> l = new ArrayList<>();
        l.add("Java");
        l.add("C");
        l.add("Haskell");
        CV cv = new CV("2", null, null,23,"a@b.fr","01",null, l);

        //cvService.save(cv);
        cvService.delete(cv);
        CV testBook = cvService.findOne(cv.getId());
        assertNull(testBook);
    }


}
