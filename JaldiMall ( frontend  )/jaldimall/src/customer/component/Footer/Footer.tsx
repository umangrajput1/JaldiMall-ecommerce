import React from "react";

function Footer() {
  return (
    <footer className="bg-white text-gray-700 border-t border-gray-200">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-10 grid grid-cols-1 md:grid-cols-3 gap-8">
        {/* Column 1: Logo + About */}
        <div>
          <h2 className="text-2xl font-bold text-pink-600">JaldiMall</h2>
          <p className="mt-3 text-sm">
            JaldiMall is your one-stop e-commerce destination for fashion,
            electronics and more. Shop quickly and safely.
          </p>
        </div>

        {/* Column 2: Services / Links */}
        <div>
          <h3 className="text-lg font-semibold mb-3">Services</h3>
          <ul className="space-y-2 text-sm">
            <li><a href="#" className="hover:text-pink-600">Shop Categories</a></li>
            <li><a href="#" className="hover:text-pink-600">Track Orders</a></li>
            <li><a href="#" className="hover:text-pink-600">Sell on JaldiMall</a></li>
            <li><a href="#" className="hover:text-pink-600">Help Center</a></li>
            <li><a href="#" className="hover:text-pink-600">Returns & Refunds</a></li>
          </ul>
        </div>

        {/* Column 3: Contact */}
        <div>
          <h3 className="text-lg font-semibold mb-3">Contact Us</h3>
          <ul className="space-y-2 text-sm">
            <li>Email: support@jaldimall.com</li>
            <li>Phone: +91-7618652940</li>
            <li><a href="#" className="hover:text-pink-600">Follow on Instagram</a></li>
            <li><a href="#" className="hover:text-pink-600">Follow on Facebook</a></li>
          </ul>
        </div>
      </div>

      <div className="border-t border-gray-200 py-4 text-center text-xs text-gray-500">
        © {new Date().getFullYear()} JaldiMall. All rights reserved.
      </div>
    </footer>
  );
}

export default Footer;
