import React from 'react';

function CategoryGrid() {
  return (
    <div className="grid grid-cols-12 grid-rows-12 gap-2 px-5 lg:px-20 lg:h-[600px]">
      {/* Left Tall Image */}
      <div className="col-span-3 row-span-12 overflow-hidden rounded-lg">
        <img
          src="https://images.prismic.io/rigbiswas/d1c30984-e5eb-411c-b570-9b76e7cd6939_258860607_4617819691649259_3932961886037087493_n.jpg?auto=compress,format?&w=750"
          alt="Traditional Wear"
          className="w-full h-full object-cover"
        />
      </div>

      {/* Top Left Small Image */}
      <div className="col-span-2 row-span-6 overflow-hidden rounded-lg">
        <img
          src="https://www.sasitrends.com/cdn/shop/files/2327D-guaranteed-ruby-lakshmi-temple-jhumka-peacock-design-earrings-bridal-sasitrends-online-shopping_ab4488f2-0e24-429e-b6c7-3b5bffbebfdd.jpg?v=1731157190&width=1800"
          alt="Stylish Shoes"
          className="w-full h-full object-cover"
        />
      </div>

      {/* Top Center Wide Image */}
      <div className="col-span-4 row-span-6 overflow-hidden rounded-lg">
        <img
          src="https://www.shaadidukaan.com/vogue/wp-content/uploads/2020/03/Bridal-Poses-Photo.jpg"
          alt="Bridal Pose"
          className="w-full h-full object-cover"
        />
      </div>

      {/* Right Tall Image */}
      <div className="col-span-3 row-span-12 overflow-hidden rounded-lg">
        <img
          src="https://img.weddingbazaar.com/shaadisaga_production/photos/pictures/005/695/752/new_medium/storiesbyjosephradhik2.jpg?1672578282"
          alt="Wedding Moment"
          className="w-full h-full object-cover"
        />
      </div>

      {/* Bottom Center Wide Image */}
      <div className="col-span-4 row-span-6 overflow-hidden rounded-lg">
        <img
          src="https://costosoitaliano.com/cdn/shop/files/sceinteis_loafer_with_minimal_brass_wire_work_zardozi_f116cb9a-d9de-4d79-8491-b710e1be8559_1024x1024@2x.png?v=1736591555"
          alt="Designer Loafers"
          className="w-full h-full object-cover"
        />
      </div>
      <div className="col-span-2 row-span-6 overflow-hidden rounded-lg">
        <img
          src="https://m.media-amazon.com/images/I/81T2kTWgJYL._SY695_.jpg"
          alt="Stylish Shoes"
          className="w-full h-full object-cover"
        />
      </div>
    </div>
  );
}

export default CategoryGrid;
