package com.dataart.inquirer.client.view.user;

import com.dataart.inquirer.client.presenter.UserPresenter;
import com.dataart.inquirer.client.view.IView;
import com.dataart.inquirer.client.view.inquirer.datagrid.InquirerDataGridWidget;
import com.dataart.inquirer.client.view.user.widgets.UserInquirerWidget;
import com.dataart.inquirer.client.view.user.widgets.UserQuestionWidget;
import com.dataart.inquirer.shared.dto.inquirer.AnswerDTO;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.dataart.inquirer.shared.dto.inquirer.QuestionDTO;
import com.dataart.inquirer.shared.dto.user.UserAnswerDTO;
import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.dataart.inquirer.shared.dto.user.UserQuestionDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.Modal;

import java.util.*;

/**
 * @author Alterovych Ilya
 */
public class UserView extends Composite implements IView {

    interface userViewUiBinder extends UiBinder<VerticalPanel, UserView> {}
    private static userViewUiBinder ourUiBinder = GWT.create(userViewUiBinder.class);
    private final UserPresenter presenter;
    @UiField(provided = true)
    InquirerDataGridWidget dataGrid;
    @UiField
    ButtonGroup inquirerButtonGroup;
    @UiField
    Button newInquirerButton;
    @UiField
    Button unfinishedInquirerButton;
    @UiField
    Button finishedInquirerButton;
    @UiField
    HorizontalPanel selectionButtonGroup;
    @UiField
    Button startInquirerButton;
    @UiField
    VerticalPanel inquirerPanel;
    @UiField
    ButtonGroup passInquirerButtonGroup;
    @UiField
    ButtonGroup upperPassInquirerButtonGroup;
    @UiField
    Button upperGoBackButton;
    @UiField
    Button upperSaveButton;
    @UiField
    Button upperPassInquirerButton;
    @UiField
    Button goBackButton;
    @UiField
    Button saveButton;
    @UiField
    Button passInquirerButton;
    @UiField
    Modal resultModal;
    @UiField
    Label modalLabel;

    @SuppressWarnings("UnusedParameters")
      @UiHandler(value = {"passInquirerButton", "upperPassInquirerButton"})
      public void onPassButton(ClickEvent event) {
        if (Window.confirm("Вы уверены что хотите закончить?")) {
            String inquirerResult = getInquirerResults();
            if (inquirerResult == null){
                Window.alert("Вы ответили ещё не на все вопросы!");
                return;
            }
            modalLabel.setText(inquirerResult);
            resultModal.show();
            presenter.saveFinishedUserInquirer();
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler(value = {"saveButton", "upperSaveButton"})
    public void onSaveButton(ClickEvent event) {
        if (Window.confirm("Вы уверены?")) {
            presenter.saveUnfinishedUserInquirer();
        }
    }

    /**
     * Удаляет текуший опросник (из отображения)
     *
     * @param event клик на кнопке
     */
    @SuppressWarnings("UnusedParameters")
    @UiHandler(value = {"goBackButton", "upperGoBackButton"})
    public void onClearButton(ClickEvent event) {
        if (Window.confirm("Вы уверены? Все несохранённые данные будут потеряны!")) {
            resetInquirerPanel();
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("newInquirerButton")
    public void onShowNewInquirers(ClickEvent event){
        startInquirerButton.setText("ПРОЙТИ");
        presenter.getInquirerModel().
                setInquirerDTOs(presenter.getUserModel().getNewInquirerDTOs());
        dataGrid.refresh();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("unfinishedInquirerButton")
    public void onShowUnfinishedInquirers(ClickEvent event){
        startInquirerButton.setText("ПРОДОЛЖИТЬ");
        presenter.getInquirerModel().
                setInquirerDTOs(presenter.getUserModel().getUnfinishedInquirerDTOs());
        dataGrid.refresh();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("finishedInquirerButton")
    public void onShowFinishedInquirers(ClickEvent event){
        startInquirerButton.setText("ПРОЙТИ ЗАНОВО");
        presenter.getInquirerModel().
                setInquirerDTOs(presenter.getUserModel().getFinishedInquirerDTOs());
        dataGrid.refresh();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("startInquirerButton")
    public void onStartInquirer(ClickEvent event){
        if (getSelectedInquirer() == null) {
            return;
        }
        setPassInquirerButtonGroup();
        showInquirer(getSelectedInquirer());
    }

    public UserInquirerDTO createUserInquirerDTO(UserInquirerDTO userInquirer){
        String passResults = getInquirerResults();
        int thisResult = passResults == null ? 0 :
                Integer.parseInt(passResults.substring(0, passResults.indexOf('/')));
        int bestResult = thisResult > userInquirer.getBestResult() ?
                thisResult : userInquirer.getBestResult();

        UserInquirerWidget userInquirerWidget = (UserInquirerWidget)
                inquirerPanel.getWidget(0);

        List<UserQuestionDTO> userQuestionDTOs = new ArrayList<>();
        int questionIndex = 0;
        for (Widget nextQuestion : userInquirerWidget.getQuestionPanel()){
            if (nextQuestion instanceof UserQuestionWidget){
                UserQuestionWidget userQuestionWidget = (UserQuestionWidget) nextQuestion;
                UserQuestionDTO userQuestionDTO = new UserQuestionDTO(
                        userInquirer.getId());

                for (AnswerDTO answerDTO : getSelectedInquirer()
                        .getQuestionsList().get(questionIndex).getAnswersList()){
                    userQuestionDTO.getAnswersList().add(new UserAnswerDTO(
                            userInquirer.getId(), userQuestionWidget.getAnswersMap()
                            .get(answerDTO.getDescription())));
                }
                userQuestionDTOs.add(userQuestionDTO);
                ++questionIndex;
            }
        }

        return new UserInquirerDTO(userInquirer.getId(), userInquirer.isFinished(),
        bestResult, userQuestionDTOs,
        presenter.getUserModel().getLoggedInUserDTO(),
        presenter.getInquirerModel().getSelectedInquirerDTO());
    }

    public UserInquirerDTO createUserInquirerDTO(boolean isFinished) {
        String passResults = getInquirerResults();
        int bestResult = 0;
        if (isFinished) {
            bestResult = passResults == null ? 0 :
                    Integer.parseInt(passResults.substring(0, passResults.indexOf('/')));
        }

        UserInquirerWidget userInquirerWidget = (UserInquirerWidget)
                inquirerPanel.getWidget(0);

        List<UserQuestionDTO> userQuestionDTOs = new ArrayList<>();
        int questionIndex = 0;
        for (Widget nextQuestion : userInquirerWidget.getQuestionPanel()){
            if (nextQuestion instanceof UserQuestionWidget){
                UserQuestionWidget userQuestionWidget = (UserQuestionWidget) nextQuestion;
                UserQuestionDTO userQuestionDTO = new UserQuestionDTO();

                for (AnswerDTO answerDTO : getSelectedInquirer()
                        .getQuestionsList().get(questionIndex).getAnswersList()){
                    userQuestionDTO.getAnswersList().add(
                            new UserAnswerDTO(userQuestionWidget.getAnswersMap()
                                    .get(answerDTO.getDescription())));
                }
                userQuestionDTOs.add(userQuestionDTO);
                ++questionIndex;
            }
        }

        return new UserInquirerDTO(isFinished, bestResult, userQuestionDTOs,
        presenter.getUserModel().getLoggedInUserDTO(),
        presenter.getInquirerModel().getSelectedInquirerDTO());
    }

    private String getInquirerResults() {
        boolean isAllQuestionHasAnswers = true;
        List<QuestionDTO> questionDTOs = getSelectedInquirer().getQuestionsList();
        VerticalPanel questionPanel = ((UserInquirerWidget)inquirerPanel.getWidget(0))
                .getQuestionPanel();
        List<UserQuestionWidget> questionWidgets =  new ArrayList<>();
        for (Widget widget : questionPanel){
            if (widget instanceof UserQuestionWidget){
                questionWidgets.add((UserQuestionWidget) widget);
            }
        }

        int totalQuestionsCount = questionDTOs.size();
        int rightQuestionCount = 0;

        for (int i = 0; i < questionDTOs.size(); i++) {
            List<AnswerDTO> answerDTOs = questionDTOs.get(i).getAnswersList();
            Map<String, Boolean> answersMap = questionWidgets.get(i).getAnswersMap();
            if (answersMap.isEmpty()){
                questionWidgets.get(i).getAnswerPanel().addStyleName("error-text-field");
                questionWidgets.get(i).getAnswerPanel().getElement()
                        .setPropertyString("borderRadius", "4px");
                isAllQuestionHasAnswers = false;
                continue;
            }

            boolean isRightAnswer = true;
            for(AnswerDTO answerDTO : answerDTOs){
                if (answerDTO.isRightAnswer() ==
                        answersMap.get(answerDTO.getDescription())){
                    continue;
                }
                isRightAnswer = false;
            }
            if (isRightAnswer){
                rightQuestionCount++;
            }
        }
        return isAllQuestionHasAnswers ? rightQuestionCount + "/" + totalQuestionsCount
                + " (" + (int)((double)rightQuestionCount / (double)totalQuestionsCount
                * 100) + "%)" : null;
    }

    private void showInquirer(InquirerDTO selectedInquirer) {
        inquirerPanel.add(new UserInquirerWidget(selectedInquirer));
    }

    public void resetInquirerPanel() {
        presenter.initUpdateView();

        passInquirerButtonGroup.setVisible(false);
        upperPassInquirerButtonGroup.setVisible(false);

        startInquirerButton.setVisible(true);
        selectionButtonGroup.setVisible(true);
        dataGrid.setVisible(true);
        dataGrid.resetSelection();
        inquirerPanel.clear();
    }

    private void setPassInquirerButtonGroup() {
        passInquirerButtonGroup.setVisible(true);
        upperPassInquirerButtonGroup.setVisible(true);

        startInquirerButton.setVisible(false);
        selectionButtonGroup.setVisible(false);
        dataGrid.setVisible(false);
    }

    private InquirerDTO getSelectedInquirer() {
        InquirerDTO inquirerDTO = presenter.getInquirerModel().getSelectedInquirerDTO();
        if (inquirerDTO == null) {
            Window.alert("Сначала выберите существующий опросник из базы данных");
            return null;
        }
        return inquirerDTO;
    }

    @UiConstructor
    public UserView(UserPresenter presenter) {
        this.presenter = presenter;
        dataGrid = new InquirerDataGridWidget(presenter.getInquirerModel(), false,
                presenter.getUserModel().getLoggedInUserDTO());
    }

    @Override
    public void init() {
        dataGrid.init();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void refresh() {
        dataGrid.refresh(presenter.getUserModel().getNewInquirerDTOs());
    }
}