package com.test.service;

import com.test.entity.Newcat;
import com.test.entity.vo.FeedbackModel;
import com.test.mapper.Cat_feedbackMapper;
import com.test.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

public class Cat_feedbackService {
    public static FeedbackModel save_feedback(String name, String variety, String message, String address, byte[] imageByte1, byte[] imageByte2) {
        FeedbackModel feedbackModel = new FeedbackModel();
        Newcat newcat = new Newcat();
        newcat.setName(name);
        newcat.setVariety(variety);
        newcat.setAddress(address);
        newcat.setMessage(message);
        newcat.setImage1(imageByte1);
        newcat.setImage2(imageByte2);
        try (SqlSession sqlSession = GetSqlSession.createSqlSession()) {
            Cat_feedbackMapper catFeedbackMapper = sqlSession.getMapper(Cat_feedbackMapper.class);
            int result = catFeedbackMapper.insertCat(newcat);
            System.out.println("Result of insertCat: " + result);
            feedbackModel.setCode(1);
            feedbackModel.setMsg("反馈成功！");
        } catch (Exception e) {
            e.printStackTrace();
            feedbackModel.setCode(0);
            feedbackModel.setMsg("反馈失败，请联系网站管理员！");
        }
        return feedbackModel;
    }
}
