<#if classInfo.frontCodeStyle == "2">
    .m-portlet {
    padding-bottom: 165px;
    margin-right: 20px;
    height: 100%;
    background: #fff;
    .list-header-box {
    margin-bottom: 20px;
    position: relative;
    .top-right-btn {
    position: absolute;
    top: 0;
    right: 15px;
    }
    }

    .form-control {
    padding: 8px 1rem;
    }
    .row {
    margin-bottom: 16px;
    }
    }

    app-blue-btn {
    .btn {
    border-radius: 16px;
    }
    }

<#else >
    .error_message {
    margin-left: 16.7%;
    }
    .ant-form-item {
    margin: 24px 0 0;
    }

    .modal-body-cus {
    padding: 20px 31px 40px;
    }

    .image-tips {
    font-size: 12px;
    font-weight: 400;
    color: #586480;
    margin-bottom: 20px;
    margin-top: 7px;
    }

    .date-product-picker {
    width: 100%;
    }

    .file-upload {
    margin-left: 120px;
    }

    .modal-spin-wrap {
    position: relative;
    }
    .spin-mask {
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, 0.6);
    position: absolute;
    top: 0;
    left: 0;
    z-index: 100;
    display: flex;
    justify-content: center;
    align-items: center;
    }


</#if>