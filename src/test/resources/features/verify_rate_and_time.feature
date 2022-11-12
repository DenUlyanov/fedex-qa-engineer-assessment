Feature: Verify rate and transit time functionality

  Scenario Outline: Verify shipping rates
    Given customer is on default home page
    When customer calculates shipping rate from "<From>" and "<To>" for "<Packaging>"
    Then customer can see shipment price

    Examples:
      | From      | To        | Packaging      |
      | Amsterdam | Rotterdam | FedEx Box      |
      | Rotterdam | Utrecht   | FedEx Envelope |
      | Utrecht   | Rotterdam | FedEx Pak      |
      | Rotterdam | Amsterdam | FedEx Tube     |
