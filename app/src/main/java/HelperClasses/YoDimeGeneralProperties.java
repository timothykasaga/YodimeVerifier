package HelperClasses;

import android.os.Build;

/**
 * Created by XT on 10/12/2015.
 */
public class YoDimeGeneralProperties {
    //private static final String site_root = "http://10.0.2.2:8080/YODIMEROOT/";
    //private static final String site_root = "http://192.168.0.100:8080/YODIMEROOT/";
      //  private static final String site_root = "http://192.168.0.114:8080/YODIMEROOT/";
   //  private static final String site_root = "http://192.168.43.195:8080/YODIMEROOT/";
  //  private static final String site_root = "http://192.168.101.1:8080/YODIMEROOT/";
   //private static final String site_root = "http://192.168.43.21:8080/YODIMEROOT/";
    //private static final String site_root = "http://dev.yodime.com:8080/";
    private static final String site_root = "https://www.yodime.com/";

    private static String manufacturer = Build.MANUFACTURER;
    private static String model = Build.MODEL;
    private static String serial_number=Build.SERIAL;
    private static String brand=Build.BRAND;

    private static String mobile_number="";
    private static final String yodime_app_verion = "10.0.0";



    public static String getYodime_app_verion() {
        return yodime_app_verion;
    }

    //new app 9.0.*
    private static final String yodime_login_url = site_root+"AndroidLogInServlet";
    private static final String yodime_register_url = site_root+"AndroidRegisterServlet";
    private static final String yodime_account_history_url = site_root+"AndroidAccountHistoryHandlerServlet";
    private static final String yodime_airtime_payment_url = site_root+"AndroidAirtimePaymentServlet";
    private static final String yodime_umeme_verification_url = site_root+"AndroidUmemePrePaidCustomerVerificationServlet";
    private static final String yodime_umeme_payment_url = site_root+"AndroidUmemePrePaidCustomerPaymentServlet";
    private static final String yodime_startimes_payment_url = site_root+"AndroidStartimesPaymentServlet";
    private static final String yodime_gotv_payment_url = site_root+"AndroidGotvPaymentServlet";
    private static final String yodime_dstv_payment_url = site_root+"AndroidDstvPaymentServlet";
    private static final String yodime_data_payment_url = site_root+"AndroidDataPaymentServlet";
    private static final String yodime_reseller_topup_url = site_root+"AndroidResellerAccountTopUpServlet";
    private static final String yodime_personal_topup_url = site_root+"AndroidPersonalMemberDeposit";
    private static final String Yodime_nwsc_verification_url = site_root+"AndroidNwscCustomerVerificationServlet";
    private static final String Yodime_nwsc_verification_nov_18_url = site_root+"AndroidNwscCustomerVerificationNov18Servlet";
    private static final String yodime_nwsc_payment_url = site_root+"AndroidNwscCustomerPaymentServlet";
    private static final String yodime_umeme_post_paid_verification_url = site_root+"AndroidUmemePostPaidCustomerVerificationServlet";
    private static final String yodime_umeme_post_paid_verification_nov_18_url = site_root+"AndroidUmemePostPaidCustomerVerificationNov18Servlet";
    private static final String yodime_umeme_post_paid_payment_url = site_root+"AndroidUmemePostPaidCustomerPaymentServlet";
    private static final String yodime_umeme_yaka_paid_verification_nov_18_url = site_root+"AndroidUmemeYakaPaidCustomerVerificationNov18Servlet";
    private static final String yodime_umeme_yaka_paid_verification_url = site_root+"AndroidUmemeYakaPaidCustomerVerificationServlet";
    private static final String yodime_umeme_yaka_paid_payment_url = site_root+"AndroidUmemeYakaPaidCustomerPaymentServlet";
    private static final String yodime_utility_transaction_url = site_root+"PendingUtilityHandler";
    private static final String yodime_payTvVerification_url = site_root+"AndroidPaytvVerificationServlet";
    private static final String yodime_agents_url = site_root+"AndroidAgentsList";
    private static final String yodime_agent_registration_url= site_root+"AndroidAgentRegistrationServlet";
    private static final String yodime_agent_activation_url= site_root+"AndroidActivateAgent";
    private static final String yodime_float_transfer_url= site_root+"AndroidFloatTransferServlet";
    private static final String yodime_commision_transfer_url= site_root+"AndroidCommisionTransferToFloatAccount";
    private static final String yodime_password_transfer_url= site_root+"AndroidCommisionTransferToFloatAccount";
    private static final String yodime_payTvValidation_url= site_root+"AndroidPayTvVerificationServlet";
    private static final String yodime_float_transfer_password_setup_url= site_root+"AndroidFloatTransferPasswordSetupServlet";
    private static final String yodime_load_agents_list_url= site_root+"AndroidLoadAgentsList";
    private static final String yodime_load_agents_commision_lisyt_url= site_root+"AndroidLoadAgentsCommissionList";
    private static final String yodime_load_providers_url= site_root+"AndroidListsProvider";
    private static final String yodime_check_top_up_activation_url= site_root+"AndroidUtilityAvailablityChecker";
    private static final String yodime_fees_and_taxes_validation_url= site_root+"AndroidTuitionAndFeesValidationServlet";
    private static final String yodime_fees_and_taxes_payment_url= site_root+"AndroidFeesAndTutionPaymentServlet";
    private static final String yodime_reset_password_url= site_root+"AndroidForgotPasswordServlet";
    private static final String yodime_zuku_payment_url= site_root+"AndroidZukuTvPayment";
    private static final String yodime_change_password_url= site_root+"AndroidChangePasswordServlet";
    private static final String yodime_preferences_url= site_root+"AndroidPreferencesLoaderServlet";
    private static final String yodime_resellers_registered_by_merchant_list_url= site_root+"AndroidResellerBySuperAgentList";
    private static final String yodime_resellers_register_by_merchant_list_url= site_root+"AndroidRegisterResellerByMerchant";
    private static final String yodime_transfer_funds_user_verification_url= site_root+"AndroidUserVerificationServlet";
    private static final String yodime_statement_url= site_root+"AndroidStatementRequestServlet";
    private static final String yodime_search_url= site_root+"AndroidSearchRequestRequestServlet";
    private static final String yodime_mobile_money_url= site_root+"AndroidMobileMoneyVerificationServelet";
    //Timothy
    private static final String yodime_ticket_selling_verification_url = site_root+"AndroidTicketSellingServlet";
    private static final String yodime_ticket_selling_confirmation_url = site_root+"AndroidTicketSellingconfirmationServlet";
    private static final String yodime_merchant_ticket_verification_url = site_root+"AndroidTicketVerificationValidationServlet";
    private static final String yodime_ticket_verification_url  = site_root+"AndroidTicketVerificationServlet";
    private static String yodime_ticket_from_server_url  = site_root+"AndroidTicketGetTicketsFromServerServlet";

    public static String getYodime_mobile_money_url() {
        return yodime_mobile_money_url;
    }


    /*//new app  8.6.0
    private static final String yodime_login_url = site_root+"AndroidLogInServlet_new";
    private static final String yodime_test_url= site_root+"AndroidPaytvTest";
    private static final String yodime_register_url = site_root+"AndroidRegisterServlet_new";
    private static final String yodime_account_history_url = site_root+"AndroidAccountHistoryHandlerServlet_new";
    private static final String yodime_airtime_payment_url = site_root+"AndroidAirtimePaymentServlet_new";
    private static final String yodime_umeme_verification_url = site_root+"AndroidUmemePrePaidCustomerVerificationServlet_new";
    private static final String yodime_umeme_payment_url = site_root+"AndroidUmemePrePaidCustomerPaymentServlet_new_enc";
    private static final String yodime_startimes_payment_url = site_root+"AndroidStartimesPaymentServlet_new";
    private static final String yodime_gotv_payment_url = site_root+"AndroidGotvPaymentServlet_new";
    private static final String yodime_dstv_payment_url = site_root+"AndroidDstvPaymentServlet_new";
    private static final String yodime_data_payment_url = site_root+"AndroidDataPaymentServlet_new";
    private static final String yodime_reseller_topup_url = site_root+"AndroidResellerAccountTopUpServlet_new";
    private static final String Yodime_nwsc_verification_url = site_root+"AndroidNwscCustomerVerificationServlet_new";
    private static final String yodime_nwsc_payment_url = site_root+"AndroidNwscCustomerPaymentServlet_new";
    private static final String yodime_umeme_post_paid_verification_url = site_root+"AndroidUmemePostPaidCustomerVerificationServlet_new";
    private static final String yodime_umeme_post_paid_payment_url = site_root+"AndroidUmemePostPaidCustomerPaymentServlet_new";
    private static final String yodime_umeme_yaka_paid_verification_url = site_root+"AndroidUmemeYakaPaidCustomerVerificationServlet_new";
    private static final String yodime_umeme_yaka_paid_payment_url = site_root+"AndroidUmemeYakaPaidCustomerPaymentServlet_new";
    private static final String yodime_utility_transaction_url = site_root+"PendingUtilityHandler_new";
*/


  /*//old app
    private static final String yodime_login_url = site_root+"AndroidLogInServlet";
    private static final String yodime_register_url = site_root+"AndroidRegisterServlet";
    private static final String yodime_account_history_url = site_root+"AndroidAccountHistoryHandlerServlet";
    private static final String yodime_airtime_payment_url = site_root+"AndroidAirtimePaymentServlet";
    private static final String yodime_umeme_verification_url = site_root+"AndroidUmemePrePaidCustomerVerificationServlet";
    private static final String yodime_umeme_payment_url = site_root+"AndroidUmemePrePaidCustomerPaymentServlet";
    private static final String yodime_startimes_payment_url = site_root+"AndroidStartimesPaymentServlet";
    private static final String yodime_gotv_payment_url = site_root+"AndroidGotvPaymentServlet";
    private static final String yodime_dstv_payment_url = site_root+"AndroidDstvPaymentServlet";
    private static final String yodime_data_payment_url = site_root+"AndroidDataPaymentServlet";
    private static final String yodime_reseller_topup_url = site_root+"AndroidResellerAccountTopUpServlet";
    private static final String Yodime_nwsc_verification_url = site_root+"AndroidNwscCustomerVerificationServlet";
    private static final String yodime_nwsc_payment_url = site_root+"AndroidNwscCustomerPaymentServlet";
    private static final String yodime_umeme_post_paid_verification_url = site_root+"AndroidUmemePostPaidCustomerVerificationServlet";
    private static final String yodime_umeme_post_paid_payment_url = site_root+"AndroidUmemePostPaidCustomerPaymentServlet";
    private static final String yodime_umeme_yaka_paid_verification_url = site_root+"AndroidUmemeYakaPaidCustomerVerificationServlet";
    private static final String yodime_umeme_yaka_paid_payment_url = site_root+"AndroidUmemeYakaPaidCustomerPaymentServlet";
    private static final String yodime_utility_transaction_url = site_root+"PendingUtilityHandler";*/

    public static String getYodime_utility_transaction_url() {
        return yodime_utility_transaction_url;
    }

    public static String getYodime_umeme_yaka_paid_verification_url() {
        return yodime_umeme_yaka_paid_verification_url;
    }

    public static String getYodime_umeme_yaka_paid_payment_url() {
        return yodime_umeme_yaka_paid_payment_url;
    }

    public static String getYodime_umeme_post_paid_verification_url() {
        return yodime_umeme_post_paid_verification_url;
    }

    public static String getYodime_umeme_post_paid_payment_url() {
        return yodime_umeme_post_paid_payment_url;
    }

    public static String getYodime_nwsc_verification_nov_18_url() {
        return Yodime_nwsc_verification_nov_18_url;
    }

    public static String getYodime_umeme_post_paid_verification_nov_18_url() {
        return yodime_umeme_post_paid_verification_nov_18_url;
    }

    public static String getYodime_umeme_yaka_paid_verification_nov_18_url() {
        return yodime_umeme_yaka_paid_verification_nov_18_url;
    }

    public static String getYodime_nwsc_payment_url() {
        return yodime_nwsc_payment_url;
    }

    public static String getYodime_register_url() {
        return yodime_register_url;
    }

    public static String getYodime_reseller_topup_url() {
        return yodime_reseller_topup_url;
    }

    public static String getYodime_data_payment_url() {
        return yodime_data_payment_url;
    }

    public static String getYodime_login_url()
    {
        return yodime_login_url;
    }

    public static String getYodime_account_history_url()
    {
        return yodime_account_history_url;
    }

    public static String getYodime_airtime_payment_url() {
        return yodime_airtime_payment_url;
    }

    public static String getYodime_umeme_verification_url() {
        return yodime_umeme_verification_url;
    }

    public static String getYodime_umeme_payment_url() {
        return yodime_umeme_payment_url;
    }

    public static String getYodime_startimes_payment_url() {
        return yodime_startimes_payment_url;
    }

    public static String getYodime_gotv_payment_url() {
        return yodime_gotv_payment_url;
    }

    public static String getYodime_dstv_payment_url() {
        return yodime_dstv_payment_url;
    }

    public static String getYodime_nwsc_verification_url() {
        return Yodime_nwsc_verification_url;
    }

    public static String getYodime_agents_url() {
        return yodime_agents_url;
    }

    public static String getYodime_agent_registration_url() {
        return yodime_agent_registration_url;
    }

    public static String getYodime_agent_activation_url() {
        return yodime_agent_activation_url;
    }

    public static String getYodime_float_transfer_url() {
        return yodime_float_transfer_url;
    }

    public static String getYodime_commision_transfer_url() {
        return yodime_commision_transfer_url;
    }

    public static String getYodime_password_transfer_url() {
        return yodime_password_transfer_url;
    }

    public static String getYodime_payTvValidation_url() {
        return yodime_payTvValidation_url;
    }

    public static String getYodime_payTvVerification_url() {
        return yodime_payTvVerification_url;
    }

    public static String getYodime_float_transfer_password_setup_url() {
        return yodime_float_transfer_password_setup_url;
    }

    public static String getYodime_load_agents_list_url() {
        return yodime_load_agents_list_url;
    }

    public static String getYodime_load_agents_commision_lisyt_url() {
        return yodime_load_agents_commision_lisyt_url;
    }

    public static String getYodime_load_providers_url() {
        return yodime_load_providers_url;
    }

    public static String getYodime_check_top_up_activation_url() {
        return yodime_check_top_up_activation_url;
    }

    public static String getYodime_fees_and_taxes_validation_url() {
        return yodime_fees_and_taxes_validation_url;
    }

    public static String getYodime_fees_and_taxes_payment_url() {
        return yodime_fees_and_taxes_payment_url;
    }

    public static String getYodime_reset_password_url() {
        return yodime_reset_password_url;
    }

    public static String getYodime_zuku_payment_url() {
        return yodime_zuku_payment_url;
    }

    public static String getYodime_change_password_url() {
        return yodime_change_password_url;
    }

    public static String getManufacturer() {
        return manufacturer;
    }

    public static String getModel() {
        return model;
    }

    public static String getSerial_number() {
        return serial_number;
    }

    public static String getBrand() {
        return brand;
    }

    public static String getYodime_preferences_url() {
        return yodime_preferences_url;
    }

    public static String getYodime_resellers_registered_by_merchant_list_url() {
        return yodime_resellers_registered_by_merchant_list_url;
    }


    public static String getYodime_resellers_register_by_merchant_list_url() {
        return yodime_resellers_register_by_merchant_list_url;
    }

    public static String getYodime_transfer_funds_user_verification_url() {
        return yodime_transfer_funds_user_verification_url;
    }

    public static String getYodime_statement_url() {
        return yodime_statement_url;
    }

    public static String getYodime_search_url() {
        return yodime_search_url;
    }

    public static String getYodime_personal_topup_url() {
        return yodime_personal_topup_url;
    }

    public static String getYodime_ticket_selling_verification_url() {
        return  yodime_ticket_selling_verification_url;
    }

    public static String getYodime_ticket_selling_confirmation_url() {
        return yodime_ticket_selling_confirmation_url;
    }

    public static String getYodime_merchant_ticket_verification_url() {
        return yodime_merchant_ticket_verification_url;
    }

    public static String getYodime_ticket_verification_url() {
        return yodime_ticket_verification_url;
    }

    public static String getYodime_ticket_from_server_url() {
        return yodime_ticket_from_server_url;
    }
}