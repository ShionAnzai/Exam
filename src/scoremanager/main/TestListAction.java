package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");


        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");



        TestDao testDao = new TestDao();
        ClassNumDao classDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        School school = teacher.getSchool();

        String searchType = request.getParameter("searchType");
        String year = request.getParameter("year");
        String classId = request.getParameter("classId");
        String subjectId = request.getParameter("subjectId");
        String studentNumber = request.getParameter("studentNumber");

        List<Test> testList = null;
        String error = null;

        try {
            if ("subject".equals(searchType)) {
                if (year != null && classId != null && subjectId != null &&
                        !year.isEmpty() && !classId.isEmpty() && !subjectId.isEmpty()) {

                    int entYear = Integer.parseInt(year);
                    Subject subject = new Subject();
                    subject.setCd(subjectId);

                    testList = testDao.filter(entYear, classId, subject, 0, school);
                } else {
                    error = "入学年度、クラス、科目のすべてを選択してください。";
                }
            } else if ("student".equals(searchType)) {
                if (studentNumber != null && !studentNumber.isEmpty()) {
                    testList = new ArrayList<>(); // 未実装
                } else {
                    error = "学生番号を入力してください。";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            error = "検索中にエラーが発生しました。";
        }

        List<String> classNumList = null;
        try {
            classNumList = classDao.filter(school);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Subject> subjectList = null;
        try {
            subjectList = subjectDao.filter(school);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("year", year);
        request.setAttribute("classId", classId);
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("studentNumber", studentNumber);
        request.setAttribute("searchType", searchType);
        request.setAttribute("classNumList", classNumList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("testList", testList);
        request.setAttribute("error", error);

        request.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(request, response);
    }
}
