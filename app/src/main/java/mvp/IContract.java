package mvp;

public interface IContract {
    /**
     * iview
     */
    interface iview{
        void ShowData(String message);
    }

    /**
     * ipresenter
     */
    interface ipresenter<iview>{
        void attachView(IContract.iview iview);
        void detachView(IContract.iview iview);
        void requestInfo();
    }

    /**
     * imodel
     */
    interface imodel{
        interface calllListen{
            void responMsg(String message);
        }
        void requestData(calllListen calllListen);
    }
}
