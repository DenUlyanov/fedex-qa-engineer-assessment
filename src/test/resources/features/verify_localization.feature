Feature: Verify localization of home page

  Scenario Outline: Verify home page localization URL
    When customer navigates to home page
    Then verify navigating to "<Country>" and "<Language>" results in correct "<Locale>"

    Examples:
      | Country        | Language   | Locale |
      | Netherlands    | Nederlands | nl-nl  |
      | Netherlands    | English    | en-nl  |
      | United Kingdom | English    | en-gb  |

