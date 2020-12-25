import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringToIntegerTest {
    @Test
    public void convertToIntTest(){
        int res = StringToInteger.convertToInt("188");
        int res2 = StringToInteger.convertToInt("1010");

        Assert.assertEquals("It's Ok!",188,res);
        Assert.assertEquals("It's Ok!",1010,res2);
    }

    @Test(expected = Exception.class)
    public void convertToIntTest_GreatThan32767() throws Exception {
        if (StringToInteger.convertToInt("32768")>32767){
            throw new Exception("It's greater than 32767");
        }
    }

    @Test(expected = Exception.class)
    public void convertToIntTest_LessThan32767() throws Exception {
        if (StringToInteger.convertToInt("-32768")<-32767){
            throw new Exception("It's less than 32767");
        }
    }

    @Test(expected = NumberFormatException.class)
    public void convertToIntTest_is_not_number() throws NumberFormatException {
            StringToInteger.convertToInt("6565PP66");
        }
    }


