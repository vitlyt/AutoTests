package dataproviders;

import mapping.DetailedSeekerResume;
import mapping.SeekerResume;
import org.testng.annotations.DataProvider;
import static utils.Utils.*;

import static utils.MockDataProvider.getMockDataObject;

public class ResumeDataProvider {

    private static Integer id = null;

    public static void setId(int aId){
        id = aId;
    }

    @DataProvider(name = "resumes-data-provider")
    public Object[][] getResumes(){
        SeekerResume resume = (SeekerResume)getMockDataObject("seekerResumesResponse",
                SeekerResume.class);
        resume.getPersonal().setPhotoUrl(preparePhotoURL(resume.getPersonal().getPhotoUrl()));
        return new Object[][] {{resume}};
    }

    @DataProvider(name = "resume-data-provider")
    public Object[][] getResume(){
        DetailedSeekerResume resume = (DetailedSeekerResume)getMockDataObject("seekerResumeResponse",
                DetailedSeekerResume.class);
        resume.getPersonal().setPhotoUrl(preparePhotoURL(resume.getPersonal().getPhotoUrl()));
        return new Object[][] {{resume, id}};
    }

    @DataProvider(name = "resume-data-provider-id")
    public Object[][] getId(){
        return new Object[][] {{id}};
    }

}