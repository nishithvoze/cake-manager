package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.controller.CakeController;
import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repo.CakeRepository;
import com.waracle.cakemgr.service.CakeManager;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
public class CakeManagerTest
{
    private MockMvc mockMvc;

    @InjectMocks
    private CakeManager cakeManager;

    @Mock
    private CakeController cakeController;


    @Mock
    private CakeRepository cakeRepository;

    @Before("")
    public void init(){
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cakeController).build();
    }

    @Test
    public void addNewCakeTest() throws Exception {
        CakeEntity cakeAdded =  new CakeEntity("Butterscotch","This is butter scotch cake","www.google.com");

        cakeManager.addNewCake(cakeAdded);

        Mockito.verify(cakeRepository).save(cakeAdded);

    }

    @Test
    public void findAllCakesTest() {

        List<CakeEntity> cakesList = Arrays.asList(
                new CakeEntity("Chocolate Cake","Chocolate Cake","image url 1"),
                new CakeEntity("Lemon cheesecake","A cheesecake made of lemon","image url 2"));

        Mockito.doReturn(cakesList).when(cakeRepository).findAll();

        List<CakeEntity> cakeEntityList = cakeManager.findAllCakes();

        assertNotNull(cakeEntityList);
        assertEquals(cakeEntityList.size(), 2);
        assertEquals(cakeEntityList.get(0).getTitle(), "Chocolate Cake");
        assertEquals(cakeEntityList.get(0).getDescription(), "Chocolate Cake");
        assertEquals(cakeEntityList.get(1).getTitle(), "Lemon cheesecake");
        assertEquals(cakeEntityList.get(1).getImage(), "image url 2");
    }

    @Test
    public void findCakeByTitleTest()
    {
        CakeEntity cake1 = new CakeEntity("Lemon cheesecake","A cheesecake made of lemon","image url 2");

        Mockito.doReturn(cake1).when(cakeRepository).findByTitle(Mockito.any());

        CakeEntity cake2 = cakeManager.findCakeByTitle("Lemon cheesecake");

        assertEquals(cake2.getTitle(), "Lemon cheesecake");
        assertEquals(cake2.getImage(), "image url 2");
        assertEquals(cake2.getDescription(), "A cheesecake made of lemon");
    }

    public static String objectAsJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

