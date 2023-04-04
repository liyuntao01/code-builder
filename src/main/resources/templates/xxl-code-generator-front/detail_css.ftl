<#if classInfo.frontCodeStyle == "2">
    .m-portlet {
    padding-bottom: 165px;
    margin-right: 20px;
    }
    .info-content {
    padding-top: 20px;
    padding-left: 40px;
    font-size: 14px;
    color: #586480;
    font-weight: 400;
    .image-bottom {
    padding-bottom: 20px;
    }
    }

    .group-detail {
    display: flex;
    .image-info {
    display: flex;
    flex-direction: column;
    font-size: 12px;
    text-align: center;
    margin-right: 20px;
    .image-name {
    margin-top: 12px;
    }
    }
    }

    .info-content-group {
    padding-bottom: 10px;
    .group-label {
    width: 126px;
    text-align: right;
    }
    .params-left {
    margin-left: 15px;
    }
    .group-detail {
    color: #252933;
    padding-left: 14px;
    }
    }

<#else >
    /* stylelint-disable */

    .modal-body-cus {
    padding: 20px 31px 40px;
    }

    .form-body {
    margin-bottom: 10px;
    }

    .details-padding {
    padding: 20px !important;
    }

    .text-label {
    > span {
    float: right;
    font-size: 14px;
    font-weight: 400;
    color: #586480;
    }
    }

    .group-detail {
    color: #252933;
    }

    /* stylelint-enable */
</#if>