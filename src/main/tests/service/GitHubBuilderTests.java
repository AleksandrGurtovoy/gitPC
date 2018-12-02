package service;




import org.junit.Assert;
import org.junit.Test;


public class GitHubBuilderTests {

    @Test
    public void getService_success() {
        GitHubBuilder builder = new GitHubBuilder();
        Assert.assertNotNull(builder.getService());
    }

}
