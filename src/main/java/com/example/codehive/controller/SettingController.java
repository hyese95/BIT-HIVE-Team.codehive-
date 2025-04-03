package com.example.codehive.controller;

import com.example.codehive.entity.*;
import com.example.codehive.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/setting")
@AllArgsConstructor
public class SettingController {
    private final GuideService guideService;
    private final FaqService faqService;
    private final QuestionServiceImp questionServiceImp;
    private final AnswerServiceImp answerServiceImp;
    private final UserService userService;

    @GetMapping("/main.do")
    public String main() {
        return "setting/main";
    }

    @GetMapping("/support/support_main.do")
    public String supportMain() {
        return "setting/support/support_main";
    }

    @GetMapping("/support/notice/notice.do")
    public String notice(Model model) {
        List<Guide> guides=guideService.readAll();
        model.addAttribute("guides", guides);
        return "setting/support/notice/notice";
    }
    @GetMapping("/support/notice/notice_detail.do")
    public String noticeDetail(Model model, @RequestParam long guideNo) {
        Guide guide=guideService.readById(guideNo);
        model.addAttribute("guide", guide);
        return "setting/support/notice/notice_detail";
    }
    @GetMapping("/support/faq/faq.do")
    public String faq(Model model) {
        List<Faq> faq=faqService.readAll();
        model.addAttribute("faq", faq);
        return "setting/support/faq/faq";
    }
    @GetMapping("/support/faq/faq_detail.do")
    public String faqDetail(Model model, @RequestParam long faqNo) {
        Faq faq=faqService.readById(faqNo);
        model.addAttribute("faq", faq);
        return "setting/support/faq/faq_detail";
    }
    @GetMapping("/support/notice/notice_search_result.do")
    public String noticeSearchResult(Model model, @RequestParam String keyword) {
        List<Guide> guides=guideService.readByKeyword(keyword);
        model.addAttribute("guides", guides);
        model.addAttribute("keyword", keyword);
        return "setting/support/notice/notice_search_result";
    }
    @GetMapping("/support/faq/faq_search_result.do")
    public String faqSearchResult(Model model, @RequestParam String keyword) {
        List<Faq> faqs=faqService.readByKeyword(keyword);
        model.addAttribute("faqs", faqs);
        model.addAttribute("keyword", keyword);
        return "setting/support/faq/faq_search_result";
    }
    @GetMapping("/support/qna/qna.do")
    public String qna(Model model) {
        List<Question> questions=questionServiceImp.readByUser_IdAndQuestionStatus(1, "PENDING");
        model.addAttribute("questions", questions);
        return "setting/support/qna/qna";
    }
    @GetMapping("/support/qna/qna_complete.do")
    public String qnaComplete(Model model) {
        List<Question> questions=questionServiceImp.readByUser_IdAndQuestionStatus(1, "COMPLETED");
        model.addAttribute("questions", questions);
        return "setting/support/qna/qna_complete";
    }



    @GetMapping("/support/qna/qna_detail.do")
    public String qnaDetail(Model model, @RequestParam int questionNo) {

        Question question=questionServiceImp.readById(questionNo);
        Answer answer=answerServiceImp.readByQuestion_Id(questionNo);

        model.addAttribute("question", question);
        model.addAttribute("answer", answer);


        return "setting/support/qna/qna_detail";
    }
    @GetMapping("/support/qna/question.do")
    public String question(){
        return "setting/support/qna/question";
    }

    @GetMapping("/my_info/my_info_main.do")
    public String myInfo(Model model) {

        Optional<User> userOpt=userService.readByUserNo(1);
        User user=userOpt.get();
        model.addAttribute("user", user);

        return "setting/my_info/my_info_main";
    }

    @GetMapping("/my_info/withdrawal/withdrawal.do")
    public String withdrawalPage() {
        return "setting/my_info/withdrawal/withdrawal";
    }





    @GetMapping("/support/privacy_policy.do")
    public String privacyPolicy() {
        return "setting/support/privacy_policy";
    }



    @GetMapping("/support/terms_of_service.do")
    public String termsOfService() {
        return "setting/support/terms_of_service";
    }
    @GetMapping("/support/terms/bit_hive.do")
    public String termsBitHive() {
        return "setting/support/terms/bit_hive";
    }
    @GetMapping("/support/terms/open_api.do")
    public String termsOpenApi() {
        return "setting/support/terms/open_api";
    }
    @GetMapping("/support/terms/nft.do")
    public String termsNft() {
        return "setting/support/terms/nft";
    }





}
