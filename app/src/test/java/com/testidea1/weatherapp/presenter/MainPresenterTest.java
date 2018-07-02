package com.testidea1.weatherapp.presenter;

import android.content.Context;

import com.testidea1.device.permission.PermissionAction;
import com.testidea1.domain.core.Location;
import com.testidea1.domain.usecases.base.UseCaseHandler;
import com.testidea1.domain.usecases.weather.DetermineUserLocationUseCase;
import com.testidea1.weatherapp.injection.Injection;
import com.testidea1.weatherapp.presenter.use_case.UseCaseThreadPoolScheduler;
import com.testidea1.weatherapp.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {
    @Mock
    DetermineUserLocationUseCase mLocationUseCase;
    @Mock
    MainView mView;
    @Mock
    Context mMockContext;
    @Mock
    PermissionAction mPermissionAction;
    @Mock
    MainPresenter.PermissionCallbacks mPermissionCallbacks;

    MainPresenter mPresenter;

    UseCaseHandler mUseCaseHandler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(mLocationUseCase.getRequestValues()).thenReturn(new DetermineUserLocationUseCase.RequestValues());
        mPresenter = new MainPresenter(mPermissionAction, mPermissionCallbacks, Injection.provideLocationUseCase(mMockContext));
        mUseCaseHandler = new UseCaseHandler(mock(UseCaseThreadPoolScheduler.class));
        mPresenter.attachView(mView);
    }

    @Test
    public void testInitAndAttachView() {
        verify(mView);
    }

    @Test
    public void testProgessbarIsShow() {
        mPresenter.getWeather(new Location(-25.567, -19.456, "City", "AR"));
        verify(mView).showProgressBar();
    }
}


