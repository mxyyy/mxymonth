package mvp;

import java.lang.ref.WeakReference;

public class IPresenter implements IContract.ipresenter<IContract.iview> {
    private IContract.iview iview;
    private IModel iModel;
    private WeakReference<IContract.iview> iviewWeakReference;
    private WeakReference<IContract.imodel> imodelWeakReference;

    @Override
    public void attachView(IContract.iview iview) {
        this.iview=iview;
        iModel = new IModel();
        iviewWeakReference = new WeakReference<>(iview);
        imodelWeakReference = new WeakReference<IContract.imodel>(iModel);

    }

    @Override
    public void detachView(IContract.iview iview) {

        iviewWeakReference.clear();
        imodelWeakReference.clear();
    }

    @Override
    public void requestInfo() {

        iModel.requestData(new IContract.imodel.calllListen() {
            @Override
            public void responMsg(String message) {
                iview.ShowData(message);
            }
        });
    }
}
