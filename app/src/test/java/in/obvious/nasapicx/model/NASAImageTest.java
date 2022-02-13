package in.obvious.nasapicx.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

public class NASAImageTest {
    private final String title = "Testing Title";
    private final String explanation = "Testing Explanation";
    private final String hdurl = "https://www.google.com/image";
    private final String copyright = "Testing Copyright";

    @Mock
    NASAImage nasaImage;

    @Mock
    List<NASAImage> nasaImages;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        when(nasaImage.getTitle()).thenReturn(title);
        when(nasaImage.getExplanation()).thenReturn(explanation);
        when(nasaImage.getHdurl()).thenReturn(hdurl);
        when(nasaImage.getCopyright()).thenReturn(copyright);
    }

    @Test
    public void testTitle(){
        when(nasaImage.getTitle()).thenReturn(title);
        Assert.assertNotEquals("Testing Title", nasaImage.getTitle());
    }

    @Test
    public void testExplanation(){
        when(nasaImage.getExplanation()).thenReturn(explanation);
        Assert.assertNotEquals("Testing Explanation", nasaImage.getExplanation());
    }

    @Test
    public void testHdurl(){
        when(nasaImage.getHdurl()).thenReturn(hdurl);
        Assert.assertNotEquals("https://www.google.com/image", nasaImage.getHdurl());
    }

    @Test
    public void testCopyright(){
        when(nasaImage.getCopyright()).thenReturn(copyright);
        Assert.assertNotEquals("Testing Copyright", nasaImage.getCopyright());
    }

    @After
    public void wrapUp(){
        nasaImage = null;
        nasaImages = null;
    }
}
