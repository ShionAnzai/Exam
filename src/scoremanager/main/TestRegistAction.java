package scoremanager.main;

import java.time.LocalDate;
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

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // フォームパラメータ取得
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス番号
        String f3 = req.getParameter("f3"); // 科目名
        String f4 = req.getParameter("f4"); // 回数

        int entYear = f1 != null && !f1.equals("0") ? Integer.parseInt(f1) : 0;
        String classNum = (f2 != null) ? f2 : "0";
        String subjectName = (f3 != null) ? f3 : "";
        int testNo = f4 != null && !f4.equals("0") ? Integer.parseInt(f4) : 0;

        // フィルター項目をJSPに戻す
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectName);
        req.setAttribute("f4", testNo);

        // 選択肢再設定
        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 1; i <= year + 1; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("ent_year_set", entYearSet);

        ClassNumDao classNumDao = new ClassNumDao();
        req.setAttribute("class_num_set", classNumDao.filter(school));

        SubjectDao subjectDao = new SubjectDao();
        req.setAttribute("subject_set", subjectDao.filter(school));

        List<Integer> noSet = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            noSet.add(i);
        }
        req.setAttribute("no_set", noSet);

        // 成績取得処理
        List<Test> tests = null;
        if (entYear != 0 && !classNum.equals("0") && !subjectName.equals("0") && testNo != 0) {
            Subject subject = subjectDao.get(subjectName, school);
            TestDao testDao = new TestDao();
            tests = testDao.filter(entYear, classNum, subject, testNo, school);
        }

        req.setAttribute("tests", tests);

        // JSPへフォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
