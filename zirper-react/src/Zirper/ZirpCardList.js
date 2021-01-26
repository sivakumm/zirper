import React from 'react';
import ZirpCard from './ZirpCard';

const ZirpCardList = ({ zirps, deleteFn, updateFn }) => {
	return (
		<div>
			{ zirps.map(zirp => <ZirpCard key={ zirp.id } originalZirp={ zirp } deleteFn={ deleteFn } updateFn={ updateFn }></ZirpCard>) }
		</div>
	);
};

export default ZirpCardList;