package com.daar.projet3;

import org.junit.runner.RunWith;
import com.daar.projet3.model.CV;
import com.daar.projet3.service.CvService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        CV cv = new CV("1001", "Jean", "Lin", l);
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
        CV cv = new CV("1001", "Jean", "Lin", l);
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
        CV cv = new CV("1001", "Jean", "Lin", l);
        cvService.save(cv);

        List<CV> byFilter = cvService.findByMotsCles("Java");
        System.out.println(byFilter.toString());
        assertThat(byFilter.size(), is(1));
    }

    @Test
    public void testFindByMots() {

        List<String> l = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l.add("Java");
        l.add("C");
        l.add("Haskell");
        l2.add("Java");

        List<CV> cvList = new ArrayList<>();
        cvList.add(new CV("1001", "Jean", "Lin", l));
        cvList.add(new CV("1002", "Claude", "Lil", l2));
        cvList.add(new CV("1003", "Pedro", "Lo", l2));
        cvList.add(new CV("1007", "George", "Lala", l));
        cvList.add(new CV("1008", "Max", "Li", l));

        for (CV cv : cvList)
            cvService.save(cv);

        Page<CV> byFilter = cvService.findByMotsCles("C", PageRequest.of(0,10));
        assertThat(byFilter.getTotalElements(), is(3L));

        Page<CV> byFilter1 = cvService.findByMotsCles("Java", PageRequest.of(0,10));
        assertThat(byFilter1.getTotalElements(), is(5L));
    }

    @Test
    public void testDelete() {
        List<String> l = new ArrayList<>();
        l.add("Java");
        l.add("C");
        l.add("Haskell");
        CV cv = new CV("1001", "Elasticsearch Basics", "Rambabu Posa", l);

        cvService.save(cv);
        cvService.delete(cv);
        CV testBook = cvService.findOne(cv.getId());
        assertNull(testBook);
    }
}
