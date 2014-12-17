package com.dataart.inquirer.client.presenter;

import com.dataart.inquirer.client.callback.CommonAsyncCallback;
import com.dataart.inquirer.client.models.InquirerModel;
import com.dataart.inquirer.client.services.AuthoritiesServiceAsync;
import com.dataart.inquirer.client.services.InquirerServiceAsync;
import com.dataart.inquirer.client.view.AccessDeniedView;
import com.dataart.inquirer.client.view.creator.CreatorView;
import com.dataart.inquirer.shared.dto.InquirerDTO;
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
//                Window.confirm("authoritiesSet: " + result);
            }
        });
    }

    public void addInquirer(InquirerDTO inquirerDTO) {
        inquirerServiceAsync.addInquirer(inquirerDTO,
                new CommonAsyncCallback<InquirerDTO>() {
            @Override
            public void onSuccess(InquirerDTO result) {
                Window.alert("Опросник \"" + result.getName() + "\" успешно добавлен.");
                view.resetInquirerPanel();
            }
        });
        //Добавление тестового опросника:
        /*
        inquirerServiceAsync.addTestInquirer(new CommonAsyncCallback<InquirerDTO>() {
            @Override
            public void onSuccess(InquirerDTO result) {
                Window.alert(String.valueOf(result));
            }
        });
        */
    }

    @Override
    public Widget getView() {
        /*TODO ВАЖНО!!! для получения доступа к функциям админа в GWTSuperDevMode
        раскоментировать следующую строку, при тестировании в деплой моде на
        томкате нужно её соответственно закоментить, иначе не будет работать
        авторизация Spring Security*/
        authoritiesSet.add("ROLE_ADMIN");
        if (authoritiesSet.contains("ROLE_ADMIN")) {
            updateInquirerList();
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
                initUpdateView();
            }
        });
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
            view.refresh();
        }
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
}
