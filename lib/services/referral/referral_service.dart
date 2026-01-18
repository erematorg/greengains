import 'package:flutter/foundation.dart';

class ReferralService {
  ReferralService._();

  static final ReferralService instance = ReferralService._();

  // TODO: Replace with backend-generated codes and validation.
  Future<void> registerReferralInvite({
    required String referralCode,
    required String inviterUid,
  }) async {
    debugPrint('Referral stub: invite copied for $inviterUid ($referralCode)');
  }

  // TODO: Call backend when an invite link is opened to register a conversion.
  Future<void> registerReferralOpen({
    required String referralCode,
    required String inviteeUid,
  }) async {
    debugPrint('Referral stub: invite opened by $inviteeUid ($referralCode)');
  }
}
