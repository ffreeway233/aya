/** 
 * pop.ups
 * author: 胡永伟
 * date: 2015-05-10
 * version: 2.2.0
 */
var g_popups_style = {
	'lock' : "style='z-index: 8888; position: fixed; top: 0; left: 0; right: 0; bottom: 0; background: #000; filter: alpha(opacity = 00); -moz-opacity: 0.00; opacity: 0.00; display: none;'",
	'mask' : "style='z-index: 8888; position: fixed; top: 0; left: 0; right: 0; bottom: 0; background: #000; filter: alpha(opacity = 42); -moz-opacity: 0.42; opacity: 0.42; display: none;'",
	'bkgr' : "style='z-index: 6666; position: fixed; top: 0; left: 0; right: 0; bottom: 0; background: #000; filter: alpha(opacity = 42); -moz-opacity: 0.42; opacity: 0.42; display: none;'",
	'tips' : {
		'ibox' : "style='z-index: 9999; position: fixed; width: 270px; left: 50%; font-family: serif; margin-left: -135px; color: #fff; background: #000; border-radius: 8px; filter: alpha(opacity = 75); -moz-opacity: 0.75; opacity: 0.75; font-size: 16px; display: none;'",
		'itxt' : "style='width: 88%; text-align: center; margin: 12px auto 12px auto;'",
	},
	'load' : {
		'ibox' : "style='z-index: 9999; position: fixed; width: 61.8%; height: 72px; line-height: 72px; top: 50%; left: 19.1%; margin-top: -36px; font-family: serif; font-size: 14px; border-radius: 8px 8px 8px 8px; background: #000; filter: alpha(opacity = 75); -moz-opacity: 0.75; opacity: 0.75; display: none;'",
		'iims' : "style='position: absolute; top: 14px; left: 18px; width: 44px; height: 44px;'",
		'iimg' : "style='position: absolute; width: 44px; height: 44px;'",
		'itxt' : "style='position: absolute; top: 14px; left: 78px; height: 44px; line-height: 44px; color: #fff;'"
	},
	'chio' : {
		'ibox' : "style='z-index: 9999; position: fixed; width: 270px; left: 50%; margin-left: -135px; font-family: serif; border-radius: 8px; background: #f8f8f8; display: none;'",
		'itxt' : "style='width: 100%; color: #000; padding: 21px 0px 19px 0px; border-radius: 8px 8px 0px 0px;'",
		'jtxt' : "style='width: 100%; color: #000; padding: 21px 0px 13px 0px; border-radius: 8px 8px 0px 0px;'",
		'text' : "style='text-align: center; width: 88%; font-weight: bold; font-size: 17px; margin: 0px auto;'",
		'note' : "style='text-align: center; width: 88%; font-weight: normal; font-size: 13px; margin: 15px auto 0px auto;'",
		'iopr' : "style='width: 100%; height: 44px; line-height: 44px; border-top: 1px solid #ddd; text-align: center; border-radius: 0 0 8px 8px; font-size: 17px; color: #007aff; font-weight: bold; cursor: pointer;'",
		'left' : "style='position: absolute; left: 0; width: 50%; height: 44px; line-height: 44px; text-align: center; border-radius: 0 0 0 8px; font-weight: normal; cursor: pointer;'",
		'rite' : "style='position: absolute; right: 0; width: 50%; height: 44px; line-height: 44px; text-align: center; border-left: 1px solid #ddd; border-radius: 0 0 8px 0; cursor: pointer;'",
	},
	'pull' : {
		'ibox' : "style='z-index: 7777; position: fixed; bottom: 0px; left: 8px; right: 8px; font-family: serif; display: none;'",
		'list' : "style='list-style: none; background: #f8f8f8; border-radius: 4px; color: #007aff; padding: 0px; margin: 0px 0px 8px 0px;'",
		'atag' : "style='text-decoration: none;'",
		'note' : "style='text-align: center; border-bottom: 1px solid #ddd; color: #8f8f8f; font-size: 12px; height: 44px; line-height: 44px;'",
		'item' : "style='text-align: center; border-bottom: 1px solid #ddd; color: #007aff; font-size: 20px; height: 44px; line-height: 44px; cursor: pointer;'",
		'lose' : "style='background: #fff; border-radius: 4px; text-align: center; color: #007aff; font-weight: bold; font-size: 20px; height: 44px; line-height: 44px; cursor: pointer; margin-bottom: 8px;'"
	},
	'sbox' : {
		'ibox' : "style='z-index: 7777; position: fixed; width: 270px; left: 50%; margin: 0px; border-radius: 6px; margin-left: -135px; padding: 0px; list-style: none; font-family: serif; font-weight: normal; font-size: 16px; background: #f8f8f8; display: none;'",
		'text' : "style='padding: 10px 0; border-bottom: 1px solid #f0f0f0; width: 235px; margin-left: 17px;text-align:center;'",
		'item' : "style='height: 40px; line-height: 40px; border-bottom: 1px solid #f0f0f0; width: 235px; margin-left: 17px;'",
		'unit' : "style='margin-top: 6px;'",
		'head' : "style='height: 40px; line-height: 40px; border-bottom: 1px solid #f0f0f0; width: 235px; margin: 0 auto; color: #8f8f8f; text-align: center; font-size: 18px; line-height: 42px;'",
		'lose' : "style='position: absolute; top: 3px; left: 16px; font-size: 22px; cursor: pointer;'",
		'foot' : "style='height: 40px; line-height: 40px;text-align:center; width: 235px; margin: 0 auto; color: #8f8f8f; border-bottom: none;'",
		'atag' : "style='text-decoration: none; color: #00e;'",
	},
	'pswd' : {
		'ibox' : "style='margin: 0px; padding: 0px; height: 34px; width: 235px; display: none;'",
		'hide' : "style='width: 1px; background: #fff; height: 1px; border: none; font-size: 1px; margin-top: 5px; position: absolute; filter: alpha(opacity = 00); -moz-opacity: 0.00; opacity: 0.00;'",
		'left' : "style='width: 38px; background: #fff; float: left; height: 32px; line-height: 32px; border: none; border: 1px solid #f0f0f0; border-right: none; text-align: center; border-radius: 5px 0px 0px 5px; font-size: 14px;'",
		'item' : "style='width: 38px; background: #fff; float: left; height: 32px; line-height: 32px; border: none; border: 1px solid #f0f0f0; border-right: none; text-align: center; border-radius: 0px; font-size: 14px;'",
		'rite' : "style='width: 38px; background: #fff; float: left; height: 32px; line-height: 32px; border: none; border: 1px solid #f0f0f0; text-align: center; border-radius: 0px 5px 5px 0px; font-size: 14px;'",
	},
	'code' : {
		'ibox' : "style='margin: 0px; padding: 0px; height: 34px; width: 235px; display: none;'",
		'into' : "style='height: 32px; font-size: 16px; border: none; border-radius: 0px; padding: 0 0 0 0px; border: 1px solid #f0f0f0; border-radius: 5px; float: left; width: 140px; background:#ffffff; text-align: center''",
		'send' : "style='height: 34px; line-height: 34px; width: 92px; font-size: 16px; float: right; color: #00e; text-align: center; cursor: pointer;'",
		'dono' : "style='height: 34px; line-height: 34px; width: 92px; font-size: 16px; float: right; color: #999; text-align: center; cursor: pointer;'",
	},
	'oper' : {
		'ibox' : "style='margin: 0px; padding: 0px; height: 34px; width: 235px; display: none;'",
		'left' : "style='border: 1px solid #f0f0f0; height: 32px; line-height: 32px; width: 112px; float: left; background: #fff; border-radius: 5px; color: #000; text-align: center; cursor: pointer;'",
		'rite' : "style='border: 1px solid #007aff; height: 32px; line-height: 32px; width: 112px; float: right; background: #007aff; border-radius: 5px; color: #fff; text-align: center; cursor: pointer;'",
	}
};