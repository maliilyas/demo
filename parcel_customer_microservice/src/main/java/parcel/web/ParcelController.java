package parcel.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcel.datalayer.Parcel;
import parcel.service.ParcelService;

import static com.google.common.base.Preconditions.*;

/**
 * Created by aliilyas on 03/03/2017.
 */
@RestController
@RequestMapping("/parcel")
@Api(basePath = "/parcel", value = "Parcel", description = "Api for Parcel End point.", produces = "application/json")
public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(final ParcelService parcelService) {
        this.parcelService = checkNotNull(parcelService);

    }

    @RequestMapping(value = "/accept", method = RequestMethod.POST,
            produces={"application/json"}, consumes = {"application/json"})
    @ApiOperation(value = "Accept Parcel", notes = "Accept Parcel if Customer is residing.")
    public ResponseEntity acceptParcel(@RequestBody final Parcel parcel) {
        return this.parcelService.acceptParcel(parcel);
    }

    @RequestMapping(value = "/clear/{roomNumber}/{passportId}", method = RequestMethod.GET)
    @ApiOperation(value = "Clear Parcels", notes = "Clear All Parcels related to the customer.")
    public ResponseEntity clearOutstandingParcels(@PathVariable final String roomNumber,
                                                  @PathVariable final String passportId) {
        return this.parcelService.clearAllOutstandingParcels(roomNumber, passportId);

    }
}
