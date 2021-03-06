package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.callback.CommonAsyncCallback;
import com.dataart.inquirer.client.models.InquirerModel;
import com.dataart.inquirer.client.services.AuthoritiesServiceAsync;
import com.dataart.inquirer.client.services.InquirerServiceAsync;
import com.dataart.inquirer.client.view.AccessDeniedView;
import com.dataart.inquirer.client.view.creator.CreatorView;
import com.dataart.inquirer.shared.dto.inquirer.InquirerDTO;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alterovych Ilya
 */
public final class CreatorPresenter implements IPresenter {
    private CreatorView view;
    private AuthoritiesServiceAsync authServiceAsync;
    private InquirerServiceAsync inquirerServiceAsync;
    private InquirerModel model;
    private Set<String> authoritiesSet = new HashSet<>();

    public CreatorPresenter(AuthoritiesServiceAsync authServiceAsync,
                            InquirerServiceAsync inquirerServiceAsync,
                            InquirerModel model) {
        this.authServiceAsync = authServiceAsync;
        this.inquirerServiceAsync = inquirerServiceAsync;
        this.model = model;
        updateAuthorities();
    }

    private void updateAuthorities() {
        authServiceAsync.getAuthorities(new CommonAsyncCallback<Set<String>>() {
            @Override
            public void onSuccess(Set<String> result) {
                authoritiesSet = result;
            }
        });
    }

    public void addInquirer(final InquirerDTO inquirerDTO) {
        inquirerServiceAsync.addInquirer(inquirerDTO,
                new CommonAsyncCallback<InquirerDTO>() {
                    @Override
                    public void onSuccess(InquirerDTO result) {
                        if (inquirerDTO.getId() == 0) {
                            Window.alert("Опросник \"" + result.getName() +
                                    "\" успешно добавлен.");
                        } else {
                            Window.alert("Опросник \"" + result.getName() +
                                    "\" успешно отредактирован.");
                        }
                        view.resetInquirerPanel();
                    }
                });
    }

    /**
     * Метод для добавления тестового опросника.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void addTestInquirer() {
        inquirerServiceAsync.addTestInquirer(new CommonAsyncCallback<InquirerDTO>() {
            @Override
            public void onSuccess(InquirerDTO result) {
                Window.alert("Опросник \"" + result.getName() + "\" успешно добавлен.");
                view.resetInquirerPanel();
            }
        });
    }

    @Override
    public Widget getView() {
        if (authoritiesSet.contains("ROLE_ADMIN")) {
            updateInquirerList();
            initUpdateView();
            return view.asWidget();
        } else {
            return AccessDeniedView.getInstance().asWidget();
        }
    }

    private void updateInquirerList() {
        inquirerServiceAsync.getAll(new CommonAsyncCallback<ArrayList<InquirerDTO>>() {
            @Override
            public void onSuccess(ArrayList<InquirerDTO> inquirerDTOs) {
                model.setInquirerDTOs(inquirerDTOs);
                view.refresh();
            }
        });
    }


    public InquirerModel getModel() {
        return model;
    }

    /**
     * Удаляет ВСЕ опросники из БД
     */
    @SuppressWarnings("UnusedDeclaration")
    public void deleteAllInquirers() {
        if (Window.confirm("Вы уверены?")) {
            if (Window.confirm("ВСЕ ОПРОСНИКИ БУДУТ УДАЛЕНЫ!!! ВЫ ТОЧНО УВЕРЕНЫ?")) {
                inquirerServiceAsync.deleteAllInquirers(new CommonAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        Window.alert("ALL Inquirers are deleted");
                    }
                });
            }
        }
    }

    public void deleteSelectedInquirer() {
        if (Window.confirm
                ("Вы уверены? Опросник будет полностью удалён из базы данных!")) {
            final InquirerDTO selectedInquirer = model.getSelectedInquirerDTO();
            if (selectedInquirer != null) {
                inquirerServiceAsync.deleteInquirer(selectedInquirer,
                        new CommonAsyncCallback<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                Window.alert("Опросник " + selectedInquirer.getName() +
                                        " удалён из базы данных");
                                model.setSelectedInquirerDTO(null);
                                view.resetInquirerPanel();
                            }
                        });
            } else {
                Window.alert("Сначала выберите существующий опросник из базы данных");
            }
        }
    }

    @Override
    public void initUpdateView() {
        if (view == null) {
            //create and init view
            view = new CreatorView(this);
            updateInquirerList();
            view.init();
        } else {
            //update view
            updateInquirerList();
        }
    }
}
