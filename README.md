Premium sms Java integration
==========================

Library which integrates with premium-sms : https://platnosci-online.pl/panel.html


Usage example:

           ConfigDTO config = ConfigBuilder.instance()
                   .key("YOUR KEY")
                   .partnerId("YOUR PARTNER ID")
                    .url("https://platnosci-online.pl/sms_check.php")
                   .build();
            
    
            CodeQueryDTO codeQuery = CodeQueryBuilder.instance().suffix("YOUR SUFFIX").code(
                    "USER ENTERED CODE").cost(100).build();
    
            CodeVerifier verifier = new CodeVerifier(config);
    
            if(verifier.verify(codeQuery).getVerificationStatus().equals(VerificationStatus.SUCCESS)) {
                //Give profit's for user
            }
            
See configuration.png for platnosci-online.pl configuration panel.

## Screenshot for platnosci-online.pl panel configuration
![screenshot](https://raw.githubusercontent.com/khipis/premiumsms/master/configuration.png)
   
#Links
    https://platnosci-online.pl/
    https://platnosci-online.pl/faq.html