/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.it.v1

import org.truelicense.core.V1CompressionTestContext
import org.truelicense.it.core.CodecTestSuite
import org.junit.runner._
import org.scalatest.junit._

/** @author Christian Schlichtherle */
@RunWith(classOf[JUnitRunner])
class V1CompressionTest
extends CodecTestSuite
   with V1TestContext
   with V1CompressionTestContext